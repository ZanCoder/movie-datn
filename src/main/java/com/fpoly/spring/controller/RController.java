package com.fpoly.spring.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpoly.spring.captcha.ICaptchaService;
import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.Coin_Transaction_HistoryDAO;
import com.fpoly.spring.dao.CountryDAO;
import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Episode;
import com.fpoly.spring.response.ValidateResponse;
import com.fpoly.spring.utils.CardType;

@RestController
public class RController {
	@Autowired
	MovieDAO movieDao;
	
	@Autowired
	Movie_EpisodeDAO movie_episodeDao;
	
	@Autowired
	GenreDAO genreDao;
	
	@Autowired
	CountryDAO countryDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	Coin_Transaction_HistoryDAO transactionDao;
	
	@Autowired
	Movie_Purchase_HistoryDAO movie_purchaseDao;
	
	@RequestMapping(value="/search-movie", method=RequestMethod.POST)
	@ResponseBody
	public List<Movie> searchMovie(HttpServletRequest request){
		String title = request.getParameter("title");
		List<Movie> movies = movieDao.findByTitleLike("%"+title+"%");
		return movies;
	}
	
	@PostMapping("/change-server")
	@ResponseBody
	public String changeServer(HttpServletRequest request) {
		Integer movie = Integer.parseInt(request.getParameter("movie"));
		Integer server = Integer.parseInt(request.getParameter("server"));
		Integer ep = Integer.parseInt(request.getParameter("ep"));
		Movie_Episode movie_ep = movie_episodeDao.findByMovieAndServerAndEp(movie, server, ep);
		
		return movie_ep.getApi_key();
	}
	
	@RequestMapping(value="/genre-more", method=RequestMethod.GET)
	@ResponseBody
	public List<Genre> genreMore() {
		List<Genre> genres = genreDao.findAll();
		return genres;
	}
	
	@RequestMapping(value="/country-more", method=RequestMethod.GET)
	@ResponseBody
	public List<Country> countryMore() {
		List<Country> countries = countryDao.findAll();
		return countries;
	}
	
	@RequestMapping(value="/recharge", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse rechareCoins(HttpServletRequest request) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		ValidateResponse response = new ValidateResponse();
		
		String bank = request.getParameter("bank");
		String account_bank = request.getParameter("account");
		Double coin = Double.parseDouble(request.getParameter("coin"));
		String recaptcha = request.getParameter("recaptcha");
		
		if(account_bank == "") {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Account number is required.");
            }});
		}else if(CardType.detect(account_bank) == CardType.UNKNOWN) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Incorrect account number.");
            }});
		}else if(CardType.detect(account_bank).toString().trim().compareToIgnoreCase(bank.trim()) != 0) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", bank + " account number is not correct.");
            }});
		}else if(coin == 0) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "please choose DEPOSIT amount.");
            }});
		}else if(recaptcha == "") {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Recaptcha is required.");
            }});
		}else {
			try {
				accountDao.rechargeCoin(account.getId(), coin);
				transactionDao.saveTransaction(coin, account.getId());
				
				response.setValidated(true);
			}
			catch (Exception e) {
				response.setValidated(false);
	            response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", "Some thing wrong!");
	            }});
			}
		}
		return response;
	}
	
	@RequestMapping(value="/purchase", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse purchaseMovie(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		Integer movie_id = Integer.parseInt(request.getParameter("movie_id"));
		String recaptcha = request.getParameter("recaptcha");
		
		Movie movie = movieDao.findById(movie_id).get();
		
		if(recaptcha == "") {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Recaptcha is required.");
            }});
		}else if(movie.getBudget() > account.getBudget()) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Not enough coins! Please recharge more coins");
            }});
		}else {
			try {
				accountDao.rechargeCoin(account.getId(), Double.parseDouble(new DecimalFormat("#.##").format(account.getBudget() - movie.getBudget())));
				movie_purchaseDao.savePurchased(account.getId(), movie.getId());
				
				response.setValidated(true);
			}
			catch (Exception e) {
				response.setValidated(false);
	            response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", "Some thing wrong!");
	            }});
			}
		}
		
		return response;
	}
}
