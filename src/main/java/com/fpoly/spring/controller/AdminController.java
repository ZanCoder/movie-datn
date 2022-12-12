package com.fpoly.spring.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.Account_RoleDAO;
import com.fpoly.spring.dao.Coin_Transaction_HistoryDAO;
import com.fpoly.spring.dao.CountryDAO;
import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_CountryDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_GenreDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.dao.Movie_RateDAO;
import com.fpoly.spring.dao.Notification_MovieDAO;
import com.fpoly.spring.dao.QualityDAO;
import com.fpoly.spring.dao.ServerDAO;
import com.fpoly.spring.dao.StatusDAO;
import com.fpoly.spring.dao.TypeDAO;
import com.fpoly.spring.form.AdminAccountForm;
import com.fpoly.spring.form.AdminMovieEpisodeForm;
import com.fpoly.spring.form.AdminMovieForm;
import com.fpoly.spring.form.AdminNotificationMovieForm;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Country;
import com.fpoly.spring.model.Movie_Episode;
import com.fpoly.spring.model.Movie_Genre;
import com.fpoly.spring.model.Notification_Movie;
import com.fpoly.spring.response.ValidateResponse;
import com.fpoly.spring.validator.AdminAccountValidator;
import com.fpoly.spring.validator.RegisterValidator;

@Controller
public class AdminController {
	private EmailValidator emailValidator = EmailValidator.getInstance();
	
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
	
	@Autowired
	QualityDAO qualityDao;
	
	@Autowired
	StatusDAO statusDao;
	
	@Autowired
	TypeDAO typeDao;
	
	@Autowired
	GenreDAO genreDao;
	
	@Autowired
	CountryDAO countryDao;
	
	@Autowired
	Movie_GenreDAO movie_genreDao;
	
	@Autowired
	Movie_CountryDAO movie_countryDao;
	
	@Autowired
	ServerDAO serverDao;
	
	@Autowired
	Notification_MovieDAO noti_movieDao;
	
	@Autowired
	Account_RoleDAO account_roleDao;
	
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
	
	@GetMapping("/admin/dashboard")
	public String admin(Model model) {
		model.addAttribute("movieDao", movieDao);
		model.addAttribute("movie_rateDao", movie_rateDao);
		model.addAttribute("accountDao", accountDao);
		model.addAttribute("topAccounts", accountDao.getMostPower().stream().limit(5).collect(Collectors.toList()));
		model.addAttribute("revenue", cthDao.revenue());
		model.addAttribute("revenue_mom", cthDao.revenue_MOM());
		model.addAttribute("revenue_dod", cthDao.revenue_DOD());
		model.addAttribute("sales", mphDao.sales());
		model.addAttribute("sales_mom", mphDao.sales_MOM());
		model.addAttribute("sales_dod", mphDao.sales_DOD());
		model.addAttribute("purchase", mphDao.purchase());
		model.addAttribute("purchase_mom", mphDao.purchase_MOM());
		model.addAttribute("purchase_dod", mphDao.purchase_DOD());
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
				model.addAttribute("adminAccountForm", new AdminAccountForm());
				model.addAttribute("error", null);
			}
			catch (Exception e) {
				model.addAttribute("error", e.getMessage());
			}
		}
		
		return "/admin/account-form";
	}
	
	@GetMapping("/admin/account-table")
	public String accountTable(Model model, @RequestParam("q") Optional<String> q, @RequestParam("page") Optional<Integer> currentPage, HttpServletRequest request) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 5);
		
		Page<Account> accounts;
		if(q.orElse(null) == null) {
			accounts = accountDao.findAllOrderByJoinedDate(pageable);
		}else {
			accounts = accountDao.findAllLikeUsernameOrderByJoinedDate("%" + q.orElse("") + "%", pageable);
		}
		
		model.addAttribute("url", "admin-table");
		model.addAttribute("accounts", accounts);
		model.addAttribute("pages", accounts);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("q", q.orElse(null));
		
		return "/admin/account-table";
	}
	
	@PostMapping("/edit_admin_account")
	@ResponseBody
	public ValidateResponse editAdminAccount(HttpServletRequest request) {
		ValidateResponse response = new ValidateResponse();
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    Account account = accountDao.findByEmail(loggedInUser.getName());
		
	    int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String avatar = request.getParameter("avatar");
		double budget = Double.parseDouble(request.getParameter("budget") == null ? "0" : request.getParameter("budget"));
		boolean status = Boolean.parseBoolean(request.getParameter("status"));
		
		if(account.getRole().getId() == 2 || account.getRole().getId() == 3) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "You don't have permission to edit.");
            }});
		}else if(accountDao.checkDuplicateUsernameExceptID(username, id) != 0) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Username is not available.");
            }});
		}else if(!this.emailValidator.isValid(email)){
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Email is not valid.");
            }});
		}else if(accountDao.checkDuplicateEmailExceptID(email, id) != 0){
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", "Email is not available.");
            }});
		}else {
			try {
				Account accountNew = accountDao.findById(id).get();
				accountNew.setUsername(username);
				accountNew.setEmail(email);
				accountNew.setAvatar(avatar);
				accountNew.setBudget(budget);
				accountNew.setStatus(status);
				
				accountDao.save(accountNew);
				
				response.setValidated(true);
	            response.setMessages(new HashMap<String, String>() {{
	                put("success", "Edit account successfully.");
	            }});
				
			}catch(Exception e) {
				response.setValidated(false);
	            response.setErrorMessages(new HashMap<String, String>() {{
	                put("error", e.getMessage());
	            }});
			}
		}
		
		return response;
	}
	
	@PostMapping("/delete_admin_account")
	public String deleteAdminAccount(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Account account = accountDao.findById(id).get();
		accountDao.delete(account);
		
		return "redirect:/admin/account-table";
	}
	
	
	@GetMapping("/admin/movie-form")
	public String movieForm(Model model) {
		AdminMovieForm adminMovieForm = new AdminMovieForm();
		
		model.addAttribute("error", null);
		model.addAttribute("adminMovieForm", adminMovieForm);
		model.addAttribute("qualities", qualityDao.findAll());
		model.addAttribute("typies", typeDao.findAll());
		model.addAttribute("statues", statusDao.findAll());
		model.addAttribute("genres", genreDao.findAll());
		model.addAttribute("countries", countryDao.findAll());
		
		return "/admin/movie-form";
	}
	
	@PostMapping("/admin/movie-form")
	public String postMovieForm(Model model, @ModelAttribute("adminMovieForm") AdminMovieForm adminMovieForm) {
			Movie movie = new Movie();
			
			try {
				movie.setTitle(adminMovieForm.getTitle());
				movie.setQuality(qualityDao.findById(adminMovieForm.getQuality()).get());
				movie.setDuration_min(adminMovieForm.getDuration_min());
				movie.setDescription(adminMovieForm.getDescription());
				movie.setRelease_date(adminMovieForm.getRelease_date());
				movie.setProductions(adminMovieForm.getProductions());
				movie.setImdb_rate(adminMovieForm.getImdb_rate() == 0 ? null : adminMovieForm.getImdb_rate());
				movie.setTrailer(adminMovieForm.getTrailer());
				movie.setType(typeDao.findById(adminMovieForm.getType()).get());
				movie.setStatus(statusDao.findById(3).get());
				
				movieDao.save(movie);
				
				List<Genre> genres = adminMovieForm.getGenres().stream().filter(o -> o != null).collect(Collectors.toList());
				for(Genre g : genres) {
					Movie_Genre newg = new Movie_Genre();
					newg.setMovie(movie);
					newg.setGenre(g);
					movie_genreDao.save(newg);
				}
				
				List<Country> countries = adminMovieForm.getCountries().stream().filter(o -> o != null).collect(Collectors.toList());
				for(Country c : countries) {
					Movie_Country newc = new Movie_Country();
					newc.setMovie(movie);
					newc.setCountry(c);
					movie_countryDao.save(newc);
				}
				
				model.addAttribute("error", null);
			}catch(Exception e) {
				model.addAttribute("error", e.getMessage());
			}
			
			model.addAttribute("adminMovieForm", new AdminMovieForm());
			model.addAttribute("qualities", qualityDao.findAll());
			model.addAttribute("typies", typeDao.findAll());
			model.addAttribute("statues", statusDao.findAll());
			model.addAttribute("genres", genreDao.findAll());
			model.addAttribute("countries", countryDao.findAll());
			
		return "/admin/movie-form";
	}
	
	@GetMapping("/admin/movie-table")
	public String movieTable(Model model, @RequestParam("q") Optional<String> q, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 5);
		
		Page<Movie> movies;
		if(q.orElse(null) == null) {
			movies = movieDao.findAllOrderByAddDateDesc(pageable);
		}else {
			movies = movieDao.findAllLikeTitleOrderByAddDateDesc("%" + q.orElse("") + "%", pageable);
		}
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_epDao", movie_epDao);
		model.addAttribute("movie_rateDao", movie_rateDao);
		model.addAttribute("qualities", qualityDao.findAll());
		model.addAttribute("typies", typeDao.findAll());
		model.addAttribute("statues", statusDao.findAll());
		model.addAttribute("genres", genreDao.findAll());
		model.addAttribute("countries", countryDao.findAll());
		
		model.addAttribute("url", "admin-table");
		model.addAttribute("pages", movies);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("q", q.orElse(null));
		
		return "/admin/movie-table";
	}
	
	@PostMapping("/edit_admin_movie")
	@ResponseBody
	public ValidateResponse editAdminMovie(HttpServletRequest request) throws ParseException {
		ValidateResponse response = new ValidateResponse();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String title = request.getParameter("title");
			int quality = Integer.parseInt(request.getParameter("quality"));
			String duration_min = request.getParameter("duration_min");
			String poster = request.getParameter("poster");
			String cover = request.getParameter("cover");
			String description = request.getParameter("description");
			Date release_date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("release_date")).getTime()); 
			String productions = request.getParameter("productions");
			float budget = Float.parseFloat(request.getParameter("budget"));
			boolean vip = Boolean.parseBoolean(request.getParameter("vip"));
			Double imdb_rate = request.getParameter("imdb_rate") == null ? null : Double.parseDouble(request.getParameter("imdb_rate"));
			String trailer = request.getParameter("trailer");
			int type = Integer.parseInt(request.getParameter("type"));
			int status = Integer.parseInt(request.getParameter("status"));
			List<Integer> genres = request.getParameter("genres").isEmpty() ? new ArrayList() : Arrays.asList(request.getParameter("genres").split(","))
					.stream().map(Integer::parseInt).collect(Collectors.toList());
			List<Integer> countries = request.getParameter("countries").isEmpty() ? new ArrayList() : Arrays.asList(request.getParameter("countries").split(","))
					.stream().map(Integer::parseInt).collect(Collectors.toList());
			
			List<Movie_Genre> movie_genres = movie_genreDao.getByMovie(id);
			movie_genreDao.deleteAll(movie_genres);
			List<Movie_Country> movie_countries = movie_countryDao.getByMovie(id);
			movie_countryDao.deleteAll(movie_countries);
			
			Movie movie = movieDao.findById(id).get();
			movie.setTitle(title);
			movie.setQuality(qualityDao.findById(quality).get());
			movie.setDuration_min(duration_min);
			movie.setPoster(poster);
			movie.setCover(cover);
			movie.setDescription(description);
			movie.setRelease_date(release_date);
			movie.setProductions(productions);
			movie.setBudget(budget);
			movie.setVip(vip);
			movie.setImdb_rate(imdb_rate);
			movie.setTrailer(trailer);
			movie.setType(typeDao.findById(type).get());
			movie.setStatus(statusDao.findById(status).get());
			genres.stream().forEach(o -> { 
				Movie_Genre movie_genreNew = new Movie_Genre();
				movie_genreNew.setMovie(movie);
				movie_genreNew.setGenre(genreDao.findById(o).get());
				movie_genreDao.save(movie_genreNew);
			});
			countries.stream().forEach(o -> { 
				Movie_Country movie_countryNew = new Movie_Country();
				movie_countryNew.setMovie(movie);
				movie_countryNew.setCountry(countryDao.findById(o).get());
				movie_countryDao.save(movie_countryNew);
			});
			
			movieDao.save(movie);
			
			response.setValidated(true);
            response.setMessages(new HashMap<String, String>() {{
                put("success", "Edit movie successfully.");
            }});
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
		}
		
		return response;
	}
	
	@PostMapping("/delete_admin_movie")
	public String deleteAdminMovie(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Movie movie = movieDao.findById(id).get();
		movieDao.delete(movie);
		
		return "redirect:/admin/movie-table";
	}
	
	@GetMapping("/admin/movie-ep-form")
	public String movieEpisodeForm(Model model) {
		AdminMovieEpisodeForm adminMovieEpisodeForm = new AdminMovieEpisodeForm();
		
		model.addAttribute("error", null);
		model.addAttribute("adminMovieEpisodeForm", adminMovieEpisodeForm);
		
		return "/admin/movie-ep-form";
	}
	
	@PostMapping("/admin/movie-ep-form")
	public String postMovieEpisodeForm(Model model, @ModelAttribute("adminMovieEpisodeForm") AdminMovieEpisodeForm adminMovieEpisodeForm) {
		try {
			Movie_Episode movie_epHYD = new Movie_Episode();
			movie_epHYD.setMovie(movieDao.findById(adminMovieEpisodeForm.getMovie()).get());
			movie_epHYD.setTitle(adminMovieEpisodeForm.getTitle());
			movie_epHYD.setEp(adminMovieEpisodeForm.getEp());
			movie_epHYD.setSeason(adminMovieEpisodeForm.getSeason());
			movie_epHYD.setServer(serverDao.findById(1).get());
			movie_epHYD.setApi_key(adminMovieEpisodeForm.getServer_hyd());
			movie_epDao.save(movie_epHYD);
			
			Movie_Episode movie_epSB = new Movie_Episode();
			movie_epSB.setMovie(movieDao.findById(adminMovieEpisodeForm.getMovie()).get());
			movie_epSB.setTitle(adminMovieEpisodeForm.getTitle());
			movie_epSB.setEp(adminMovieEpisodeForm.getEp());
			movie_epSB.setSeason(adminMovieEpisodeForm.getSeason());
			movie_epSB.setServer(serverDao.findById(2).get());
			movie_epSB.setApi_key(adminMovieEpisodeForm.getServer_sb());
			movie_epDao.save(movie_epSB);
			
			model.addAttribute("error", null);
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		model.addAttribute("adminMovieEpisodeForm", new AdminMovieEpisodeForm());
		model.addAttribute("servers", serverDao.findAll());
		
		return "/admin/movie-ep-form";
	}
	
	@GetMapping("/admin/movie-ep-table")
	public String movieEpTable(Model model, @RequestParam("q") Optional<Integer> q, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 5);
		
		Page<Movie_Episode> movie_eps;
		if(q.orElse(null) == null) {
			movie_eps = movie_epDao.findAllOrderByIdDesc(pageable);
		}else {
			movie_eps = movie_epDao.findAllByMovieOrderByIdDesc(q.orElse(null), pageable);
		}
		
		model.addAttribute("url", "admin-table");
		model.addAttribute("pages", movie_eps);
		model.addAttribute("movie_eps", movie_eps);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("q", q.orElse(null));
		
		return "/admin/movie-ep-table";
	}
	
	@PostMapping("/edit_admin_movie_ep")
	@ResponseBody
	public ValidateResponse editAdminMovieEp(HttpServletRequest request) throws ParseException {
		ValidateResponse response = new ValidateResponse();
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int movie = Integer.parseInt(request.getParameter("movie"));
			String title = request.getParameter("title");
			int ep = Integer.parseInt(request.getParameter("ep"));
			int season = Integer.parseInt(request.getParameter("season"));
			String api_key = request.getParameter("api_key");
			
			Movie_Episode movie_ep = movie_epDao.findById(id).get();
			movie_ep.setMovie(movieDao.findById(movie).get());
			movie_ep.setTitle(title);	
			movie_ep.setEp(ep);
			movie_ep.setSeason(season);
			movie_ep.setApi_key(api_key);
			
			movie_epDao.save(movie_ep);
			
			response.setValidated(true);
            response.setMessages(new HashMap<String, String>() {{
                put("success", "Edit movie successfully.");
            }});
		}catch(Exception e) {
			response.setValidated(false);
            response.setErrorMessages(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
		}
		
		return response;
	}
	
	@PostMapping("/delete_admin_movie_ep")
	public String deleteAdminMovieEp(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		Movie_Episode movie_ep = movie_epDao.findById(id).get();
		movie_epDao.delete(movie_ep);
		
		return "redirect:/admin/movie-ep-table";
	}
	
	@GetMapping("/admin/notification-movie-form")
	public String notificationMovieForm(Model model) {
		AdminNotificationMovieForm adminNotificationMovieForm = new AdminNotificationMovieForm();
		
		model.addAttribute("error", null);
		model.addAttribute("adminNotificationMovieForm", adminNotificationMovieForm);
		
		return "/admin/notification-movie-form";
	}
	
	@PostMapping("/admin/notification-movie-form")
	public String postNotificationMovieForm(Model model, @ModelAttribute("adminNotificationMovieForm") AdminNotificationMovieForm adminNotificationMovieForm) {
		try {
			Integer send_to = adminNotificationMovieForm.getSend_to();
			if(send_to.equals(1)) {
				for(Account account : accountDao.findAll().stream().filter(o -> accountDao.getAchievement(o.getId()) == "New").collect(Collectors.toList())) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}else if(send_to.equals(2)) {
				for(Account account : accountDao.findAll().stream().filter(o -> accountDao.getAchievement(o.getId()) == "Angelfish").collect(Collectors.toList())) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}else if(send_to.equals(3)) {
				for(Account account : accountDao.findAll().stream().filter(o -> accountDao.getAchievement(o.getId()) == "Crab").collect(Collectors.toList())) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}else if(send_to.equals(4)) {
				for(Account account : accountDao.findAll().stream().filter(o -> accountDao.getAchievement(o.getId()) == "Starfish").collect(Collectors.toList())) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}else if(send_to.equals(5)) {
				for(Account account : accountDao.findAll().stream().filter(o -> accountDao.getAchievement(o.getId()) == "Dolphin").collect(Collectors.toList())) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}else {
				for(Account account : accountDao.findAll()) {
					Notification_Movie notification_movie = new Notification_Movie();
					notification_movie.setAccount(account);
					notification_movie.setMovie_episode(movie_epDao.findById(adminNotificationMovieForm.getMovie_ep()).get());
					notification_movie.setDescription(adminNotificationMovieForm.getDescription());
					noti_movieDao.save(notification_movie);
				}
			}
			
			model.addAttribute("error", null);
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		
		model.addAttribute("adminMovieEpisodeForm", new AdminMovieEpisodeForm());
		
		return "/admin/notification-movie-form";
	}
	
	@GetMapping("/admin/transaction-history-table")
	public String purchaseTable(Model model, @RequestParam("type") Optional<Integer> t, @RequestParam("q") Optional<String> q, @RequestParam("page") Optional<Integer> currentPage) {
		int type = t.orElse(1);
		String keyword = q.orElse("").trim();
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 5);
		
		if(type == 1) {
			model.addAttribute("coins", cthDao.findByAccountUsernameLikeOrderByTimeStampDesc("%" + keyword + "%", pageable));
			model.addAttribute("pages", cthDao.findByAccountUsernameLikeOrderByTimeStampDesc("%" + keyword + "%", pageable));
		}else {
			model.addAttribute("movies", mphDao.findByAccountUsernameLikeOderByTimeStampDesc("%" + keyword + "%", pageable));
			model.addAttribute("pages", mphDao.findByAccountUsernameLikeOderByTimeStampDesc("%" + keyword + "%", pageable));
		}
		
		model.addAttribute("url", "admin-transaction-table");
		model.addAttribute("q", keyword);
		model.addAttribute("type", type);
		model.addAttribute("currentPage", currentPage.orElse(1));

		return "/admin/transaction-history-table";
	}
	
	@GetMapping("/admin/authorize-table")
	public String authorizeTable(Model model, @RequestParam("q") Optional<String> q, @RequestParam("page") Optional<Integer> currentPage, HttpServletRequest request) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 5);
		
		Page<Account> accounts;
		if(q.orElse(null) == null) {
			accounts = accountDao.findAllOrderByJoinedDate(pageable);
		}else {
			accounts = accountDao.findAllLikeUsernameOrderByJoinedDate("%" + q.orElse("") + "%", pageable);
		}
		
		model.addAttribute("url", "admin-table");
		model.addAttribute("accounts", accounts);
		model.addAttribute("pages", accounts);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("q", q.orElse(null));
		
		return "/admin/authorize-table";
	}
	
	@GetMapping("/admin/revenue-chart")
	public String revenueChart(Model model) {
		model.addAttribute("months_data", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
										.stream().map(i -> cthDao.revenueByMonth(i))
										.collect(Collectors.toList()));
		
		List<Integer> years = IntStream.range(Year.now().getValue() - 4, Year.now().getValue() + 1).boxed().collect(Collectors.toList());
		model.addAttribute("years", years);
		model.addAttribute("years_data", years.stream()
										.map(i -> cthDao.revenueByYear(i))
										.collect(Collectors.toList()));
		return "/admin/revenue-chart";
	}
	
	@GetMapping("/admin/sales-chart")
	public String salesChart(Model model) {
		model.addAttribute("months_data", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
										.stream().map(i -> mphDao.salesByMonth(i))
										.collect(Collectors.toList()));
		
		List<Integer> years = IntStream.range(Year.now().getValue() - 4, Year.now().getValue() + 1).boxed().collect(Collectors.toList());
		model.addAttribute("years", years);
		model.addAttribute("years_data", years.stream()
										.map(i -> mphDao.salesByYear(i))
										.collect(Collectors.toList()));
		return "/admin/sales-chart";
	}
	
	@GetMapping("/admin/purchase-chart")
	public String purchaseChart(Model model) {
		model.addAttribute("months_data", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
										.stream().map(i -> mphDao.purchaseByMonth(i))
										.collect(Collectors.toList()));
		
		List<Integer> years = IntStream.range(Year.now().getValue() - 4, Year.now().getValue() + 1).boxed().collect(Collectors.toList());
		model.addAttribute("years", years);
		model.addAttribute("years_data", years.stream()
										.map(i -> mphDao.purchaseByYear(i))
										.collect(Collectors.toList()));
		return "/admin/purchase-chart";
	}
	
	@PostMapping("/set_authorize")
	@ResponseBody
	public void postAuthorizeTable(Model model, HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		int value = Integer.parseInt(request.getParameter("value"));
		
		Account account = accountDao.findById(id).get();
		account.setRole(account_roleDao.findById(value).get());
		
		accountDao.save(account);
	}
	
	@ModelAttribute
	public void foo(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    model.addAttribute("account", account);
	}
}
