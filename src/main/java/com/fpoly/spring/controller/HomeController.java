package com.fpoly.spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fpoly.spring.dao.GenreDAO;
import com.fpoly.spring.dao.MovieDAO;
import com.fpoly.spring.dao.Movie_EpisodeDAO;
import com.fpoly.spring.dao.Movie_GenreDAO;
import com.fpoly.spring.dao.ServerDAO;
import com.fpoly.spring.dao.TypeDAO;
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
	
	@PostMapping("/change-server")
	@ResponseBody
	public String changeServer(HttpServletRequest request) {
		Integer movie = Integer.parseInt(request.getParameter("movie"));
		Integer server = Integer.parseInt(request.getParameter("server"));
		Integer ep = Integer.parseInt(request.getParameter("ep"));
		Movie_Episode movie_ep = movie_episodeDao.findByMovieAndServerAndEp(movie, server, ep);
		
		return movie_ep.getApi_key();
	}
}
