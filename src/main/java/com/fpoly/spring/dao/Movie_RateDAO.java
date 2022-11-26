package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Movie_Rate;

@Repository
public interface Movie_RateDAO extends JpaRepository<Movie_Rate, Integer>{
	@Query(value="select count(*) from movie_rate "
				+ " where account = ?1 and movie = ?2", nativeQuery=true)
	Integer checkRate(int account, int movie);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into movie_rate (account, movie, rate) "
			+ " values (?1, ?2, ?3) ", nativeQuery=true)
	void saveRate(int account, int movie, double rate);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update movie_rate "
			+ " set rate = ?3"
			+ " where account = ?1 and movie = ?2 ", nativeQuery=true)
	void updateRate(int account, int movie, double rate);
	
	@Query(value="select rate from movie_rate "
			+ " where account = ?1 and movie = ?2 ", nativeQuery=true)
	Double getRate(int account, int movie);
	
	@Query(value=" select dbo.get_movie_rated(?1) ", nativeQuery=true)
	Double getMovieRated(int movie);
	
	@Query(value=" select dbo.get_total_movie_rated(?1) ", nativeQuery=true)
	Integer getTotalMovieRated(int movie);
}
