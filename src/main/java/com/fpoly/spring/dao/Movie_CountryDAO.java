package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Movie_Country;

@Repository
public interface Movie_CountryDAO extends JpaRepository<Movie_Country, Integer>{
	@Query(value="SELECT * FROM [Movie_Country] "
			+ " WHERE movie = ?1 ",
			nativeQuery=true)
	List<Movie_Country> getByMovie(Integer movie);
}
