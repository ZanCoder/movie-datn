package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Movie_Purchase_History;

@Repository
public interface Movie_Purchase_HistoryDAO extends JpaRepository<Movie_Purchase_History, Integer>{
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into movie_purchase_history (account, movie) "
			+ " values (?1, ?2) ", nativeQuery=true)
	void savePurchased(int account, int movie);
	
	@Query(value="select count(*) from movie_purchase_history "
			+ " where account = ?1 and movie = ?2 ", nativeQuery=true)
	Integer checkPurchased(int account, int movie);
}
