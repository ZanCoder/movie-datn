package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Episode;

@Repository
public interface Movie_EpisodeDAO extends JpaRepository<Movie_Episode, Integer> {
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1")
	List<Movie_Episode> findByMovie(int id);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 AND o.season = ?2")
	List<Movie_Episode> findByMovieAndSeason(int id, int season);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 AND o.server.id = ?2 AND o.ep = ?3")
	Movie_Episode findByMovieAndServerAndEp(int id, int server, int ep);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 AND o.season = ?2 AND o.ep = ?3")
	List<Movie_Episode> findByMovieAndSeasonAndEp(int id, int season, int ep);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 AND o.season = ?2 AND o.server.id = ?3")
	List<Movie_Episode> findByMovieAndSeasonAndServer(int id, int season,  int server);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 AND o.season = ?2 AND o.ep = ?3 AND o.server.id = ?4")
	Movie_Episode findByMovieAndSeasonAndEpAndServer(int id, int season, int ep, int server);
	
	@Query(value="select count(*) from (select count(season) as s from [movie_episode] where movie = ?1 group by season) c", nativeQuery=true)
	Integer getCountSeason(int id);
	
	@Query(value="SELECT o FROM Movie_Episode o ORDER BY o.id DESC")
	Page<Movie_Episode> findAllOrderByIdDesc(Pageable pageable);
	
	@Query(value="SELECT o FROM Movie_Episode o WHERE o.movie.id = ?1 ORDER BY o.id DESC")
	Page<Movie_Episode> findAllByMovieOrderByIdDesc(int movie, Pageable pageable);
}
