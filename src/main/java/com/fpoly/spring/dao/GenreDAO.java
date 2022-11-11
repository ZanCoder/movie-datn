package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Genre;
import com.fpoly.spring.model.Movie;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer>{
	@Query(value="select top 10 * from genre", nativeQuery=true)
	List<Genre> findGenreByTop10();
	
	@Query(value="SELECT o FROM Genre o WHERE o.genre_slug like ?1")
	Genre findBySlugLike(String slug);
}
