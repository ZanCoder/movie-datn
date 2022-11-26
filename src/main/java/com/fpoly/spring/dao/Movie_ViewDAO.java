package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Movie_View;

@Repository
public interface Movie_ViewDAO extends JpaRepository<Movie_View, Integer>{
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into movie_view (movie) "
			+ " values (?1) ", nativeQuery=true)
	void updateView(int movie);
}
