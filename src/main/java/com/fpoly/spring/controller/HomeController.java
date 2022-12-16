package com.fpoly.spring.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.Coin_Transaction_HistoryDAO;
import com.fpoly.spring.dao.Comment_MovieDAO;
import com.fpoly.spring.dao.Continue_WatchingDAO;
import com.fpoly.spring.dao.CountryDAO;
import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_GenreDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.dao.Movie_RateDAO;
import com.fpoly.spring.dao.Notification_MovieDAO;
import com.fpoly.spring.dao.QualityDAO;
import com.fpoly.spring.dao.ServerDAO;
import com.fpoly.spring.dao.TypeDAO;
import com.fpoly.spring.dao.Watch_ListDAO;
import com.fpoly.spring.form.ProfileForm;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Coin_Transaction_History;
import com.fpoly.spring.model.Comment_Movie;
import com.fpoly.spring.model.Continue_Watching;
import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Episode;
import com.fpoly.spring.model.Movie_Genre;
import com.fpoly.spring.model.Movie_Purchase_History;
import com.fpoly.spring.model.Notification_Movie;
import com.fpoly.spring.model.Server;
import com.fpoly.spring.model.Type;
import com.fpoly.spring.model.Watch_List;
import com.fpoly.spring.response.ValidateResponse;

@Controller
public class HomeController {
	@Autowired
	MovieDAO movieDao;
	
	@Autowired
	Movie_GenreDAO movie_genreDao;
	
	@Autowired
	Movie_EpisodeDAO movie_episodeDao;
	
	@Autowired
	ServerDAO serverDao;
	
	@Autowired
	GenreDAO genreDao;
	
	@Autowired
	TypeDAO typeDao;
	
	@Autowired
	CountryDAO countryDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	Movie_Purchase_HistoryDAO movie_purchaseDao;
	
	@Autowired
	Movie_RateDAO movie_rateDao;
	
	@Autowired
	Continue_WatchingDAO cwDao;
	
	@Autowired
	Watch_ListDAO wlDao;
	
	@Autowired
	Notification_MovieDAO noti_movieDao;
	
	@Autowired
	Coin_Transaction_HistoryDAO cthDao;
	
	@Autowired
	Movie_Purchase_HistoryDAO mphDao;
	
	@Autowired
	QualityDAO qualityDao;
	
	@Autowired
	Comment_MovieDAO cmDao;
	
	@GetMapping("/")
	public String index(Model model) {
		return "home/index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Movie> movies = movieDao.findAll();

		List<Movie> spotlights = movieDao.getSpotlights();
		List<Movie> trends = movieDao.getTrends();
		List<Movie> topIMDBs = movieDao.getTop5TopIMDB();
		List<Movie> populars = movieDao.getTop5Populars();
		List<Movie> favorites = movieDao.getTop5Favorites();
		List<Movie> completes = movieDao.getTop5Completes();
		List<Movie> latestEps = movieDao.getTop12LatestEps();
		List<Movie> newMovies = movieDao.getTop12NewMovies(); 
		List<Movie> upComings = movieDao.getTop12Upcomings();
		List<Genre> genres = genreDao.findAll();
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_genre", movie_genreDao);
		model.addAttribute("spotlights", spotlights);
		model.addAttribute("trends", trends);
		model.addAttribute("topIMDBs", topIMDBs);
		model.addAttribute("populars", populars);
		model.addAttribute("favorites", favorites);
		model.addAttribute("completes", completes);
		model.addAttribute("latestEps", latestEps);
		model.addAttribute("newMovies", newMovies);
		model.addAttribute("upComings", upComings);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("genres", genres);
		
		return "home/view";
	}
	
	@GetMapping("/movie/{slug}/{season}")
	public String detail(Model model, @PathVariable("slug") String slug, @PathVariable("season") int season) {
		Movie movie = movieDao.findBySlugLike(slug);
		List<Movie_Episode> movie_eps = movie_episodeDao.findByMovieAndSeasonAndServer(movie.getId(), season, 1);
		String[] productions = movie.getProductions().split(",");
		Integer seasons = movie_episodeDao.getCountSeason(movie.getId());
		
		List<Object[]> recommends = movieDao.getRecommends(movie.getId());
		List<Movie> recommend_movies = new ArrayList<Movie>();
		
		Iterator it = recommends.iterator();
		while(it.hasNext()){
		     Object[] line = (Object[]) it.next();
		     Movie m = new Movie();
		     m.setId(Integer.parseInt(String.valueOf(line[0])));
		     m.setTitle(String.valueOf(line[1]));
		     m.setPoster(String.valueOf(line[2]));
		     m.setSlug(String.valueOf(line[3]));
		     m.setVip(Boolean.parseBoolean(String.valueOf(line[4])));
		     m.setDuration_min(String.valueOf(line[5]));
		     m.setType(typeDao.findById(Integer.parseInt(String.valueOf(line[6]))).get());
		     m.setQuality(qualityDao.findById(Integer.parseInt(String.valueOf(line[7]))).get());
			
			recommend_movies.add(m);  
		}
		
		model.addAttribute("movie", movie);
		model.addAttribute("movie_eps", movie_eps);
		model.addAttribute("productions", productions);
		model.addAttribute("season", season);
		model.addAttribute("seasons", seasons);
		model.addAttribute("recommend_movies", recommend_movies);
		model.addAttribute("movie_ep", movie_episodeDao);
		
		return "movie/detail";
	}
	
	@GetMapping("/watch-movie/{slug}/{se_ep}")
	public String watch(Model model, @PathVariable("slug") String slug, @PathVariable("se_ep") String se_ep) {
		try {
			Movie movie = movieDao.findBySlugLike(slug);
			String[] se = se_ep.split("-");
			Integer seasons = movie_episodeDao.getCountSeason(movie.getId());
			Integer season = Integer.parseInt(se[0]);
			Integer ep = Integer.parseInt(se[1]);
			Integer server = 1;
			
			List<Movie_Episode> movie_eps = movie_episodeDao.findByMovieAndSeasonAndServer(movie.getId(), season, server);
			Movie_Episode movie_ep = movie_episodeDao.findByMovieAndSeasonAndEpAndServer(movie.getId(), season, ep, server);
			
			List<Server> servers = serverDao.findAll();
			
			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String email = loggedInUser.getName(); 
		    Account account = accountDao.findByEmail(email);
		    
		    if(movie.isVip()) {
		    	if(account == null) {
		    		return "redirect:/home";
		    	}else if(movie_purchaseDao.checkPurchased(account.getId(), movie.getId()) == 0) {
		    		return "redirect:/home";
		    	}
		    }
		    
		    if(account != null) {
		    	Double getRate = movie_rateDao.getRate(account.getId(), movie.getId());
		    	model.addAttribute("getRate", getRate);
		    }
		    
		    Double getMovieRated = movie_rateDao.getMovieRated(movie.getId());
		    Integer getTotalMovieRated = movie_rateDao.getTotalMovieRated(movie.getId());
		    List<Movie> populars = movieDao.getTop10Populars();
		    
		    List<Object[]> recommends = movieDao.getRecommends(movie.getId());
			List<Movie> recommend_movies = new ArrayList<Movie>();
			
			Iterator it = recommends.iterator();
			while(it.hasNext()){
			     Object[] line = (Object[]) it.next();
			     Movie m = new Movie();
			     m.setId(Integer.parseInt(String.valueOf(line[0])));
			     m.setTitle(String.valueOf(line[1]));
			     m.setPoster(String.valueOf(line[2]));
			     m.setSlug(String.valueOf(line[3]));
			     m.setVip(Boolean.parseBoolean(String.valueOf(line[4])));
			     m.setDuration_min(String.valueOf(line[5]));
			     m.setType(typeDao.findById(Integer.parseInt(String.valueOf(line[6]))).get());
			     m.setQuality(qualityDao.findById(Integer.parseInt(String.valueOf(line[7]))).get());
				
				recommend_movies.add(m);  
			}
		    
		    model.addAttribute("movie", movie);
			model.addAttribute("movie_eps", movie_eps);
			model.addAttribute("movie_ep", movie_ep);
			model.addAttribute("seasons", seasons);
			model.addAttribute("season", season);
			model.addAttribute("ep", ep);
			model.addAttribute("server", serverDao.findById(server).get());
			model.addAttribute("servers", servers);
			model.addAttribute("getMovieRated", getMovieRated);
			model.addAttribute("getTotalMovieRated", getTotalMovieRated);
			model.addAttribute("populars", populars);
			model.addAttribute("movie_episodeDao", movie_episodeDao);
			model.addAttribute("recommend_movies", recommend_movies.stream().limit(12).collect(Collectors.toList()));
		}catch(Exception e) {
			return "redirect:/home";
		}
		
		return "movie/watch_movie";
	}
	
	@RequestMapping(value="/qtip", method=RequestMethod.POST)
    public String qtip(Model model, HttpServletRequest request) {
		Movie movie = movieDao.findById(Integer.parseInt(request.getParameter("id"))).get();
		List<Movie_Episode> movie_eps = movie_episodeDao.findByMovieAndSeasonAndServer(movie.getId(), 1, 1);
		
		model.addAttribute("movie", movie); 
		model.addAttribute("movie_eps", movie_eps);
		
		return "utilities/qtip-popup";
    }
	
	@GetMapping("/search")
	public String search_result(Model model, @RequestParam("keyword") String keyword, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByTitleLike("%"+keyword+"%", pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", keyword);
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "search");
		model.addAttribute("keyword", keyword);
		
		return "movie/search_results";
	}
	
	@GetMapping("/genre/{slug}")
	public String genre_result(Model model, @PathVariable("slug") String slug, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Object[]> pages = movieDao.getByGenre(slug, pageable);
		
		List<Movie> movies = new ArrayList();
		
		Iterator it = pages.getContent().iterator();
		while(it.hasNext()){
		     Object[] line = (Object[]) it.next();
		     Movie m = new Movie();
		     m.setId(Integer.parseInt(String.valueOf(line[0])));
		     m.setTitle(String.valueOf(line[1]));
		     m.setPoster(String.valueOf(line[2]));
		     m.setSlug(String.valueOf(line[3]));
		     m.setVip(Boolean.parseBoolean(String.valueOf(line[4])));
		     m.setDuration_min(String.valueOf(line[5]));
		     m.setType(typeDao.findById(Integer.parseInt(String.valueOf(line[6]))).get());
		     m.setQuality(qualityDao.findById(Integer.parseInt(String.valueOf(line[7]))).get());
			
			movies.add(m);  
		}
		
		model.addAttribute("topic", genreDao.findBySlugLike(slug).getGenre_name());
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "genre");
		model.addAttribute("slug", slug);
		
		return "movie/search_results";
	}
	
	@GetMapping("/country/{slug}")
	public String country_result(Model model, @PathVariable("slug") String slug, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Object[]> pages = movieDao.getByCountry(slug, pageable);
		
		List<Movie> movies = new ArrayList();
		
		Iterator it = pages.getContent().iterator();
		while(it.hasNext()){
		     Object[] line = (Object[]) it.next();
		     Movie m = new Movie();
		     m.setId(Integer.parseInt(String.valueOf(line[0])));
		     m.setTitle(String.valueOf(line[1]));
		     m.setPoster(String.valueOf(line[2]));
		     m.setSlug(String.valueOf(line[3]));
		     m.setVip(Boolean.parseBoolean(String.valueOf(line[4])));
		     m.setDuration_min(String.valueOf(line[5]));
		     m.setType(typeDao.findById(Integer.parseInt(String.valueOf(line[6]))).get());
		     m.setQuality(qualityDao.findById(Integer.parseInt(String.valueOf(line[7]))).get());
			
			movies.add(m);  
		}
		
		model.addAttribute("topic", countryDao.findBySlugLike(slug).getCountry_name());
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "country");
		model.addAttribute("slug", slug);
		
		return "movie/search_results";
	}
	
	@GetMapping("/movies")
	public String movies_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByTypeOrderByAddDateDesc(1, pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Movies");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results";
	}
	
	@GetMapping("/vip-movies")
	public String vip_movies_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByVipOrderByAddDateDesc(true, pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Vip Movies");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results";
	}
	
	@GetMapping("/free-movies")
	public String free_movies_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByVipOrderByAddDateDesc(false, pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Free Movies");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results";
	}
	
	@GetMapping("/tv-series")
	public String tvseries_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByTypeOrderByAddDateDesc(2, pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "TV Series");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results";
	}
	
	@GetMapping("/top-imdb")
	public String topimdb_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.getTopIMDB(pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Top IMDB");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results"; 
	}
	
	@GetMapping("/most-popular")
	public String mostpopular_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.getPopulars(pageable);
		
		List<Movie> movies = pages.getContent();

		model.addAttribute("topic", "Most Popular");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results"; 
	}
	
	@GetMapping("/most-favorite")
	public String mostfavorite_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.getFavorites(pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Most Favorite");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results"; 
	}
	
	@GetMapping("/completed")
	public String completed_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.getCompletes(pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Completed");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results"; 
	}
	
	@GetMapping("/top-upcoming")
	public String topupcoming_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.getUpcomings(pageable);
		
		List<Movie> movies = pages.getContent();
		
		model.addAttribute("topic", "Top Upcoming");
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		return "movie/search_results"; 
	}
	
	@GetMapping("/random")
	public String randomMovie() {
		Random generator = new Random();
		int movieId = generator.nextInt(movieDao.getCountAll()) + 1;
		
		Movie movie = movieDao.findById(movieId).get();
		
		return String.format("redirect:/movie/%s/1", movie.getSlug());
	}
	
	@GetMapping("/user/{link}")
	public String viewProfile(Model model, @PathVariable("link") String link, 
			@RequestParam("page") Optional<Integer> currentPage,
			@RequestParam("type") Optional<String> type,
			@RequestParam("sort") Optional<String> sort) {
		
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 12);
	    
	    switch(link) {
	    	case "continue-watching":
			    Page<Continue_Watching> cws = cwDao.findByAccountOrderByDesc(account.getId(), pageable);
		    	model.addAttribute("pages", cws);
		    	model.addAttribute("cws", cws);
		    	break;
	    	case "watch-list":
			    Page<Watch_List> wls;
		    	
			    switch(sort.orElse("")) {
			    	case "recently-added":
			    		wls = wlDao.findByAccountAndStatusLikeOrderByAddDate(account.getId(), type.orElse("%"), pageable);
			    		break;
			    	case "score":
			    		wls = wlDao.findByAccountAndStatusLikeOrderByScore(account.getId(), type.orElse("%"), pageable);
			    		break;
			    	case "a-z":
			    		wls = wlDao.findByAccountAndStatusLikeOrderByMovieTitle(account.getId(), type.orElse("%"), pageable);
			    		break;
			    	case "released-date":
			    		wls = wlDao.findByAccountAndStatusLikeOrderByReleaseDate(account.getId(), type.orElse("%"), pageable);
			    		break;
			    	case "most-watched":
			    		wls = wlDao.findByAccountAndStatusLikeOrderByMostWatched(account.getId(), type.orElse("%"), pageable);
			    		break;
		    		default:
		    			wls = wlDao.findByAccountAndStatusLike(account.getId(), type.orElse("%"), pageable);
		    			break;
			    }
			    
			    model.addAttribute("pages", wls);
		    	model.addAttribute("wls", wls);
		    	model.addAttribute("type", type.orElse("%"));
		    	model.addAttribute("sort", sort.orElse(""));
	    		break;
	    	case "notification":
	    		Page<Notification_Movie> noti_movies = noti_movieDao.findByAccountOrderByTimestamp(account.getId(), pageable);
	    		int noti_count = noti_movieDao.findByAccountAndNewNoti(account.getId(), true).size();
	    		model.addAttribute("pages", noti_movies);
		    	model.addAttribute("noti_movies", noti_movies);
		    	model.addAttribute("noti_count", noti_count);
		    	model.addAttribute("type", type.orElse("1"));
	    		break;
	    	case "purchase-history":
	    		if(type.orElse("").compareTo("") == 0 || type.orElse("").compareTo("1") == 0) {
	    			Page<Movie_Purchase_History> mphs = mphDao.findByAccountOderByTimeStampDesc(account.getId(), pageable);
	    			model.addAttribute("pages", mphs);
		    		model.addAttribute("mphs", mphs);
		    		model.addAttribute("type", type.orElse("1"));
		    		model.addAttribute("total", mphDao.getTotalByAccount(account.getId()));
	    		}else {
		    		Page<Coin_Transaction_History> cths = cthDao.findByAccountOrderByTimeStampDesc(account.getId(), pageable);
		    		model.addAttribute("pages", cths);
		    		model.addAttribute("cths", cths);
		    		model.addAttribute("type", type.orElse("2"));
		    		model.addAttribute("total", cthDao.getTotalByAccount(account.getId()));
	    		}
	    		break;
	    }
	    
		model.addAttribute("link", link);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "user");
		
		return "user/user";
	}
	
	@RequestMapping(value="/change_tab_mostviewed", method=RequestMethod.POST)
	public String changeTabMostviewed(Model model, HttpServletRequest request) {
		
		String type = request.getParameter("type");
		
		List<Movie> movies = new ArrayList();
		if(type.compareTo("today") == 0) {
			movies = movieDao.getTop10MostViewsByToday();
		}
		if(type.compareTo("week") == 0) {
			movies = movieDao.getTop10MostViewsByWeek();
		}
		if(type.compareTo("month") == 0) {
			movies = movieDao.getTop10MostViewsByMonth();
		}
		
		model.addAttribute("type", type);
		model.addAttribute("mostViews", movies);
		
		return "/utilities/most_viewed-table";
	}
	
	@RequestMapping(value="/load_comment_movie", method=RequestMethod.GET)
	public String loadCommentMovie(Model model, HttpServletRequest request) {
		
		Pageable pageable = PageRequest.of(0, 2);
		
		int movieEpId = Integer.parseInt(request.getParameter("movieEpId"));
		Movie_Episode movie_ep = movie_episodeDao.findById(movieEpId).get();
		Page<Comment_Movie> comment_movies = cmDao.findByMovieEpisode(movieEpId, pageable);
		
		model.addAttribute("movieEpId", movieEpId);
	    model.addAttribute("comment_movies", comment_movies);
	    model.addAttribute("movie_ep", movie_ep);
	    model.addAttribute("totalPage", comment_movies.getTotalPages());
	    
		return "/movie/comment-movie";
	}
	
	@RequestMapping(value="send_comment_movie", method=RequestMethod.POST)
	public String sendCommentMovie(Model model, HttpServletRequest request) {

			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
		    String email = loggedInUser.getName(); 
		    Account account = accountDao.findByEmail(email);
			
			int movieEpId = Integer.parseInt(request.getParameter("movieEpId"));
			int parentId = Integer.parseInt(request.getParameter("parentId") == null ? "0" : request.getParameter("parentId"));
			String text = request.getParameter("text");
			String tagName = request.getParameter("tagName");
			Boolean spoil = Boolean.parseBoolean(request.getParameter("spoil"));
			
			Comment_Movie cmNew = new Comment_Movie();
			
			if(parentId != 0) {
				Comment_Movie cm = new Comment_Movie();
				cm.setAccount(account);
				cm.setMovie_episode(movie_episodeDao.findById(movieEpId).get());
				cm.setParent_cmt(cmDao.findById(parentId).get());
				cm.setText(text);
				cm.setSpoil(spoil);
				cm.setTag_name(tagName);
				cm.setTimestamp(new Timestamp(System.currentTimeMillis()));
				
				cmNew = cmDao.save(cm);
				model.addAttribute("reply", cmNew);
				model.addAttribute("c", cmNew.getParent_cmt());
				return "/movie/comment-movie-reply-box";
			} else {
				Comment_Movie cm = new Comment_Movie();
				cm.setAccount(account);
				cm.setMovie_episode(movie_episodeDao.findById(movieEpId).get());
				cm.setText(text);
				cm.setSpoil(spoil);
				cm.setTimestamp(new Timestamp(System.currentTimeMillis()));
				
				cmNew = cmDao.save(cm);
				model.addAttribute("c", cmNew);
				model.addAttribute("replies", cmNew.getReplies());
				return "/movie/comment-movie-box";
			}
	}
	
	@RequestMapping(value="view_more_movie_comments", method=RequestMethod.POST, produces = {"text/html;charset=utf-8"})
	public String viewMoreMovieComments(Model model, HttpServletRequest request) {
		int movieEpId = Integer.parseInt(request.getParameter("movieEpId"));
		int datepage = Integer.parseInt(request.getParameter("page")) + 1;
		Pageable pageable = PageRequest.of(datepage, 2);
		
		Movie_Episode movie_ep = movie_episodeDao.findById(movieEpId).get();
		Page<Comment_Movie> comment_movies = cmDao.findByMovieEpisode(movieEpId, pageable);
		
		model.addAttribute("movieEpId", movieEpId);
	    model.addAttribute("comment_movies", comment_movies);
	    model.addAttribute("movie_ep", movie_ep);
	    
		return "/movie/comment-movie-more";
	}
	
	@GetMapping(value= {"/community", "/watch2gether"})
	public String comingSoon() {
		return "/coming_soon";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	@GetMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	    
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "/error/404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "/error/500";
	        }
	    }
	    
	    return "/error/404";
	}
	
	@ModelAttribute
	public void foo(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    Account account = accountDao.findByEmail(email);
	    
	    if(account != null) {
	    	Pageable pageable = PageRequest.of(0, 5);
	    	Page<Notification_Movie> noti_movies = noti_movieDao.findByAccountOrderByTimestamp(account.getId(), pageable);
	    	model.addAttribute("noti_movies", noti_movies);
	    	model.addAttribute("noti_movies_size", noti_movieDao.findByAccountAndNewNoti(account.getId(), true).size());
	    }
	    
	    RegisterForm registerForm = new RegisterForm();
	    ProfileForm profileForm = new ProfileForm();
	    
	    List<Genre> genres_short = genreDao.findGenreByTop10();
		List<Country> countries_short = countryDao.findCountryByTop10();
		
		List<Movie> mostViews = movieDao.getTop10MostViewsByToday();
		
		model.addAttribute("registerForm", registerForm);
		model.addAttribute("profileForm", profileForm);
	    model.addAttribute("account", account);
	    model.addAttribute("movie_purchaseDao", movie_purchaseDao);
	    model.addAttribute("genres_short", genres_short);
		model.addAttribute("countries_short", countries_short);
		model.addAttribute("movie_rateDao", movie_rateDao);
		model.addAttribute("wlDao", wlDao);
		model.addAttribute("mostViews", mostViews);
		model.addAttribute("movieDao", movieDao);
	}
}
