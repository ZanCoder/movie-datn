package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Movie_Genre;

@Repository
public interface Movie_GenreDAO extends JpaRepository<Movie_Genre, Integer>{
	@Query(value="SELECT * FROM [Movie_Genre] AS p "
			+ " JOIN [Movie] AS i ON p.movie = i.id "
			+ " WHERE i.id = ?1 ",
			nativeQuery=true)
	List<Movie_Genre> getByGenre(Integer id);
	
	@Query(value="SELECT * FROM [Movie_Genre] "
			+ " WHERE movie = ?1 ",
			nativeQuery=true)
	List<Movie_Genre> getByMovie(Integer movie);
}
