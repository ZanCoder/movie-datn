package com.fpoly.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.fpoly.spring.dao.AccountDAO;
import com.fpoly.spring.dao.CountryDAO;
import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_GenreDAO;
import com.fpoly.spring.dao.Movie_Purchase_HistoryDAO;
import com.fpoly.spring.dao.ServerDAO;
import com.fpoly.spring.dao.TypeDAO;
import com.fpoly.spring.form.RegisterForm;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Episode;
import com.fpoly.spring.model.Movie_Genre;
import com.fpoly.spring.model.Server;
import com.fpoly.spring.model.Type;

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
	
	@GetMapping("/")
	public String index(Model model) {
		return "home/index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Movie> movies = movieDao.findAll();
		
		List<Movie> spotlights = movieDao.getSpotlights();
		List<Movie> trends = movieDao.getTrends();
		List<Movie> airings = movieDao.getAirings();
		List<Movie> populars = movieDao.getPopulars();
		List<Movie> favorites = movieDao.getFavorites();
		List<Movie> completes = movieDao.getCompletes();
		List<Movie> latestEps = movieDao.getLatestEp();
		List<Movie> newMovies = movieDao.getNewMovies(); 
		List<Movie> mostViews = movieDao.getMostViews();
		
		List<Genre> genres = genreDao.findAll();
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_genre", movie_genreDao);
		model.addAttribute("spotlights", spotlights);
		model.addAttribute("trends", trends);
		model.addAttribute("airings", airings);
		model.addAttribute("populars", populars);
		model.addAttribute("favorites", favorites);
		model.addAttribute("completes", completes);
		model.addAttribute("latestEps", latestEps);
		model.addAttribute("newMovies", newMovies);
		model.addAttribute("mostViews", mostViews);
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
		     m.setType(typeDao.getOne(Integer.parseInt(String.valueOf(line[6]))));
			
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
		Movie movie = movieDao.findBySlugLike(slug);
		String[] se = se_ep.split("-");
		Integer seasons = movie_episodeDao.getCountSeason(movie.getId());
		Integer season = Integer.parseInt(se[0]);
		Integer ep = Integer.parseInt(se[1]);
		Integer server = 1;
		
		List<Movie_Episode> movie_eps = movie_episodeDao.findByMovieAndSeasonAndServer(movie.getId(), season, server);
		Movie_Episode movie_ep = movie_episodeDao.findByMovieAndSeasonAndEpAndServer(movie.getId(), season, ep, server);
		
		List<Server> servers = serverDao.findAll();
		
		model.addAttribute("movie", movie);
		model.addAttribute("movie_eps", movie_eps);
		model.addAttribute("movie_ep", movie_ep);
		model.addAttribute("seasons", seasons);
		model.addAttribute("season", season);
		model.addAttribute("ep", ep);
		model.addAttribute("server", serverDao.findById(server).get());
		model.addAttribute("servers", servers);
		
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
		
		List<Movie> mostViews = movieDao.getMostViews();
		
		model.addAttribute("topic", keyword);
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "search");
		model.addAttribute("keyword", keyword);
		
		model.addAttribute("mostViews", mostViews);
		
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
		     m.setType(typeDao.getOne(Integer.parseInt(String.valueOf(line[6]))));
			
			movies.add(m);  
		}
		
		List<Movie> mostViews = movieDao.getMostViews();
		
		model.addAttribute("topic", genreDao.findBySlugLike(slug).getGenre_name());
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "genre");
		model.addAttribute("slug", slug);
		
		model.addAttribute("mostViews", mostViews);
		
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
		     m.setType(typeDao.getOne(Integer.parseInt(String.valueOf(line[6]))));
			
			movies.add(m);  
		}
		
		List<Movie> mostViews = movieDao.getMostViews();
		
		model.addAttribute("topic", countryDao.findBySlugLike(slug).getCountry_name());
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "country");
		model.addAttribute("slug", slug);
		
		model.addAttribute("mostViews", mostViews);
		
		return "movie/search_results";
	}
	
	@GetMapping("/movies")
	public String movies_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByType(1, pageable);
		
		List<Movie> movies = pages.getContent();
		
		List<Movie> mostViews = movieDao.getMostViews();
		
		model.addAttribute("topic", "Movies");
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		model.addAttribute("mostViews", mostViews);
		
		return "movie/search_results";
	}
	
	@GetMapping("/tv-series")
	public String tvseries_result(Model model, @RequestParam("page") Optional<Integer> currentPage) {
		Pageable pageable = PageRequest.of(currentPage.orElse(1) - 1, 16);
		Page<Movie> pages = movieDao.findByType(2, pageable);
		
		List<Movie> movies = pages.getContent();
		
		List<Movie> mostViews = movieDao.getMostViews();
		
		model.addAttribute("topic", "Movies");
		
		model.addAttribute("movies", movies);
		model.addAttribute("movie_eps", movie_episodeDao);
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage", currentPage.orElse(1));
		model.addAttribute("url", "movies");
		
		model.addAttribute("mostViews", mostViews);
		
		return "movie/search_results";
	}
	
	@ModelAttribute
	public void foo(Model model) {
		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
	    String email = loggedInUser.getName(); 
	    
	    Account account = accountDao.findByEmail(email);
	    RegisterForm form = new RegisterForm();
	    
	    List<Genre> genres_short = genreDao.findGenreByTop10();
		List<Country> countries_short = countryDao.findCountryByTop10();
		
		model.addAttribute("registerForm", form);
	    model.addAttribute("account", account);
	    model.addAttribute("movie_purchaseDao", movie_purchaseDao);
	    model.addAttribute("genres_short", genres_short);
		model.addAttribute("countries_short", countries_short);
	}
}
