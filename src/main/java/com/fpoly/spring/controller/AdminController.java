package com.fpoly.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.Coin_Transaction_HistoryDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.dao.Movie_RateDAO;
import com.fpoly.spring.form.AdminAccountForm;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.validator.AdminAccountValidator;
import com.fpoly.spring.validator.RegisterValidator;

@Controller
public class AdminController {
	@Autowired
	private AdminAccountValidator adminAccountValidator;
	
	@Autowired
    private MessageSource messageSource;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	Coin_Transaction_HistoryDAO cthDao;
	
	@Autowired
	Movie_Purchase_HistoryDAO mphDao;
	
	@Autowired
	MovieDAO movieDao;
	
	@Autowired
	Movie_EpisodeDAO movie_epDao;
	
	@Autowired
	Movie_RateDAO movie_rateDao;
	
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		
		System.out.println("Target=" + target);

		if (target.getClass() == AdminAccountForm.class) {
			dataBinder.setValidator(adminAccountValidator);
		}
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("movieDao", movieDao);
		model.addAttribute("movie_rateDao", movie_rateDao);
		model.addAttribute("accountDao", accountDao);
		model.addAttribute("topAccounts", accountDao.getMostPower().stream().limit(5).collect(Collectors.toList()));
		model.addAttribute("revenue", cthDao.revenue());
		model.addAttribute("sales", mphDao.sales());
		model.addAttribute("purchase", mphDao.purchase());
		model.addAttribute("visaCount", cthDao.tranfer("%VISA%"));
		model.addAttribute("mastercardCount", cthDao.tranfer("%MASTERCARD%"));
		model.addAttribute("american_expressCount", cthDao.tranfer("%AMERICAN_EXPRESS%"));
		model.addAttribute("discoverCount", cthDao.tranfer("%DISCOVER%"));
		model.addAttribute("jcbCount", cthDao.tranfer("%JCB%"));
		model.addAttribute("transaction_histories", cthDao.findAllOrderByTimeStampDesc()
												.stream().limit(2).collect(Collectors.toList()));
		model.addAttribute("topMoviePurchased", mphDao.getTopMoviePurchased()
											.stream().map(o -> movieDao.findById(o).get())
											.collect(Collectors.toList()));

		return "/admin/index";
	}
	
	@GetMapping("/admin/account-form")
	public String accountForm(Model model) {
		AdminAccountForm adminAccountForm = new AdminAccountForm();
		
		model.addAttribute("adminAccountForm", adminAccountForm);
		model.addAttribute("error", null);
		
		return "/admin/account-form";
	}
	
	@PostMapping("/admin/account-form")
	public String postAccountForm(Model model, @ModelAttribute("adminAccountForm") @Valid AdminAccountForm adminAccountForm, BindingResult result) {
		if (result.hasErrors()) {
			List<FieldError> errorLists = result.getFieldErrors();
			List<String> errorMaps = new ArrayList();
			errorLists.forEach((error) -> errorMaps.add(messageSource.getMessage(error.getCode(), null, Locale.ENGLISH)));
			
			model.addAttribute("error", errorMaps.get(0));
		}
		else {
			Account newUser = new Account();
			try {
				String encrytedPassword = this.passwordEncoder.encode(adminAccountForm.getPassword());
				
				newUser.setUsername(adminAccountForm.getUsername());
				newUser.setEmail(adminAccountForm.getEmail());
				newUser.setPassword_hash(encrytedPassword);
				
				accountDao.createAccount(newUser);
				model.addAttribute("error", null);
			}
			catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		
		return "/admin/account-form";
	}
	
	@GetMapping("/admin/movie-form")
	public String movieForm(Model model) {
		
		return "/admin/movie-form";
	}
	
	@GetMapping("/admin/account-table")
	public String accountTable(Model model) {
		model.addAttribute("accounts", accountDao.findAll());
		return "/admin/account-table";
	}
	
	@GetMapping("/admin/movie-table")
	public String movieTable(Model model) {
		model.addAttribute("movies", movieDao.findAll());
		model.addAttribute("movie_epDao", movie_epDao);
		return "/admin/movie-table";
	}
	
	@GetMapping("/admin/purchase-table")
	public String purchaseTable(Model model) {
		model.addAttribute("coins", cthDao.findAll());
		model.addAttribute("movies", mphDao.findAll());
		return "/admin/purchased-table";
	}
	
	@ModelAttribute
	public void foo(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    model.addAttribute("account", account);
	}
}
