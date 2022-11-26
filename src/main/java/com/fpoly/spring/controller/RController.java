package com.fpoly.spring.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.fpoly.spring.dao.Continue_WatchingDAO;
import com.fpoly.spring.dao.CountryDAO;
import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.dao.Movie_RateDAO;
import com.fpoly.spring.dao.Movie_ViewDAO;
import com.fpoly.spring.dao.Notification_MovieDAO;
import com.fpoly.spring.dao.Watch_ListDAO;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Continue_Watching;
import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Episode;
import com.fpoly.spring.model.Notification_Movie;
import com.fpoly.spring.model.Watch_List;
import com.fpoly.spring.response.ValidateResponse;
import com.fpoly.spring.service.EmailSenderService;
import com.fpoly.spring.utils.CardType;

@RestController
public class RController {
	@Autowired
    PasswordEncoder passwordEncoder;
	
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
	
	@Autowired
	Movie_RateDAO movie_rateDao;
	
	@Autowired
	Movie_ViewDAO movie_viewDao;
	
	@Autowired
	Continue_WatchingDAO cwDao;
	
	@Autowired
	Watch_ListDAO wlDao;
	
	@Autowired
	Movie_EpisodeDAO movie_epDao;
	
	@Autowired
	Notification_MovieDAO noti_movieDao;
	
	@Autowired
	EmailSenderService emailSenderService;
	
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
		Integer season = Integer.parseInt(request.getParameter("season"));		
		Movie_Episode movie_ep = movie_episodeDao.findByMovieAndSeasonAndEpAndServer(movie, season, ep, server);
		
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
				accountDao.rechargeCoin(account.getId(), account.getBudget() + coin);
				transactionDao.saveTransaction(coin, account.getId(), CardType.detect(account_bank).toString());
				
				response.setValidated(true);
			}
			catch (Exception e) {
				response.setValidated(false);
	            response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", "Something went wrong! Please try again later.");
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
	                put("error", "Something went wrong! Please try again later.");
	            }});
			}
		}
		
		return response;
	}
	
	@RequestMapping(value="/rating", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse rateMovie(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		Double rate = Double.parseDouble(request.getParameter("rate"));
		Integer movieId = Integer.parseInt(request.getParameter("movieId"));
		
		Movie movie = movieDao.findById(movieId).get();
		
		try {
			Integer checkRate = movie_rateDao.checkRate(account.getId(), movie.getId());
			if(checkRate == 0)  movie_rateDao.saveRate(account.getId(), movie.getId(), rate);
			else movie_rateDao.updateRate(account.getId(), movie.getId(), rate);
			
			Double getMovieRated = movie_rateDao.getMovieRated(movie.getId());
		    Integer getTotalMovieRated = movie_rateDao.getTotalMovieRated(movie.getId());
		    
			response.setValidated(true);
			response.setMessages(new HashMap<String, String>() {{
                put("movieRated", getMovieRated.toString());
                put("totalMovieRated", getTotalMovieRated.toString());
            }});
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later.");
            }});
		}
		
		return response;
	}
	
	@RequestMapping(value="/update_view", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse updateView(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		Integer movieId = Integer.parseInt(request.getParameter("movieId"));
		Integer movieEp = Integer.parseInt(request.getParameter("movieEp"));
		Integer movieSeason = Integer.parseInt(request.getParameter("movieSeason"));
		
		Movie movie = movieDao.findById(movieId).get();
		
		try {
			Movie_Episode movie_ep = movie_epDao.findByMovieAndSeasonAndEpAndServer(movieId, movieSeason, movieEp, 1);
			
			if(account != null) {
				Continue_Watching cw = cwDao.findByAccountAndMovieEpisode(account.getId(), movie_ep.getId());
				if(cw == null) {
					Continue_Watching cwNew = new Continue_Watching();
					cwNew.setAccount(account);
					cwNew.setMovie_episode(movie_ep);
					cwNew.setView_date(new java.sql.Date(System.currentTimeMillis()));
					cwDao.save(cwNew);
				}else {
					cw.setView_date(new java.sql.Date(System.currentTimeMillis()));
					cwDao.save(cw);
				}
			}
			
			movie_viewDao.updateView(movie.getId());
			response.setValidated(true);
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later.");
            }});
		}
		
		return response;
	}
	
	@RequestMapping(value="/update_profile", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse updateProfile(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		String username = request.getParameter("username");
		String current_pass = request.getParameter("currentpass");
		String new_pass = request.getParameter("pass");
		String confirm_new_pass = request.getParameter("confirm");
		String avatar = request.getParameter("avatar");
		
		try {
			String username_updated, password_updated, avatar_updated = "";
			
			if(accountDao.checkDuplicateUsername(username, account.getId()) != 0) {
				throw new Exception("Username is not available.");
			}
			
			if(!current_pass.isEmpty() || !new_pass.isEmpty() || !confirm_new_pass.isEmpty()) {
				if(account.checkPassword(current_pass)) {
					if(new_pass.compareTo(confirm_new_pass) == 0) {
						if(new_pass.isEmpty())	password_updated = account.getPassword_hash();
						else {
							String encrytedPassword = passwordEncoder.encode(new_pass);
							password_updated = encrytedPassword;
						}
					}else {
						System.out.println(new_pass + " - " + confirm_new_pass);
			            throw new Exception("Current password does not match the confirm password.");
					}
				}else {
		            throw new Exception("Incorrect Password.");
				}
			}else {
				password_updated = account.getPassword_hash();
			}
			
			username_updated = username;
			if(avatar.isEmpty()) avatar_updated = account.getAvatar();
			else avatar_updated = avatar;
			
			accountDao.updateProfile(account.getId(), username_updated, password_updated, avatar_updated);
			
			response.setValidated(true);
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", e.getMessage()); 
            }});
		}
		
		return response;
	}
	
	@RequestMapping(value="/remove_cw", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse removeCw(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
		
		int movieId = Integer.parseInt(request.getParameter("movieId"));
		
		try {
			Continue_Watching cw = cwDao.findByAccountAndMovie(account.getId(), movieId);
			cwDao.deleteById(cw.getId());
			response.setValidated(true);
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later."); 
            }});
		}
		
		return response;
	}
	
	@RequestMapping(value="/update_wl", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse updateWl(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    int movieId = Integer.parseInt(request.getParameter("movieId"));
	    String type = request.getParameter("type");
	    
	    try {
	    	Watch_List wl = wlDao.findByAccountAndMovie(account.getId(), movieId);
	    	if(wl == null) {
	    		Watch_List wlNew = new Watch_List();
	    		wlNew.setAccount(account);
	    		wlNew.setMovie(movieDao.findById(movieId).get());
	    		wlNew.setStatus(type);
	    		wlDao.save(wlNew);
	    		response.setValidated(true);
				response.setMessages(new HashMap<String, String>() {{
	                put("message", "This movie has been added to Watch List."); 
	            }});
	    	}else if(type.compareTo("0") == 0) {
	    		wlDao.delete(wl);
	    		response.setValidated(true);
	    		response.setMessages(new HashMap<String, String>() {{
	                put("message", "This movie has been removed from Watch List."); 
	            }});
	    	}else {
	    		wl.setStatus(type);
				wlDao.save(wl);
				response.setValidated(true);
				response.setMessages(new HashMap<String, String>() {{
	                put("message", "This movie has been added to Watch List."); 
	            }});
	    	}
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later."); 
            }});
		}
	    
	    return response;
	}
	
	@RequestMapping(value="/mark_noti_movie", method=RequestMethod.POST)
	public void markNotiMovie() {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    	    
	    noti_movieDao.markAll(account.getId());
	}
	
	@RequestMapping(value="/wl_pub_toggle", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse wlPubToggle() {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    try {
	    	account.setPublic_wl(!account.getPublic_wl());
	    	accountDao.save(account);
	    	response.setValidated(true);
	    }catch(Exception e) {
	    	response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later."); 
            }});
	    }
	    
	    return response;
	}
	
	@RequestMapping(value="/report_movie", method=RequestMethod.POST)
	@ResponseBody
	public ValidateResponse reportMovie(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    int movieEpId = Integer.parseInt(request.getParameter("movieEpId"));
	    String reportMovieValues =  request.getParameter("reportMovieValues");
	    String reportAudioValues =  request.getParameter("reportAudioValues");
	    String reportOther =  request.getParameter("reportOther");
	    String recaptcha = request.getParameter("recaptcha");
	    
	    if(reportMovieValues.isEmpty() || reportAudioValues.isEmpty() || recaptcha.isEmpty()) {
	    	response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Please fill all required fields."); 
            }});
            
            return response;
	    }
	    
	    try {
	    	String pattern = String
	    			.format("<h2> %s sent report issues </h2>"
	    					+ "<ul>"
	    					+ "<li>Movie Episode Id: %d</li>"
	    					+ "<li>Video: %s </li>"
	    					+ "<li> Audio: %s </li>"
	    					+ "<li> Other: %s </li>"
	    					+ "</ul>", account.getUsername(), movieEpId, reportMovieValues, reportAudioValues, reportOther);
	    	emailSenderService.sendMailHtml(account.getEmail(), "Report movie issues", pattern);
			
			response.setValidated(true);
	    }catch(Exception e) {
	    	response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Something went wrong! Please try again later."); 
            }});
	    }
		
		return response;
	}
}
