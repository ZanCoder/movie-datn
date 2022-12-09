package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("SELECT o FROM Movie_Purchase_History o WHERE o.account.id = ?1 ORDER BY o.timestamp DESC")
	Page<Movie_Purchase_History> findByAccountOderByTimeStampDesc(int account, Pageable pageable);
	
	@Query(value="select ISNULL(sum(budget), 0) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id ", nativeQuery=true)
	float sales();
	
	@Query(value="select dbo.get_sales_mom() ", nativeQuery=true)
	float sales_MOM();
	
	@Query(value="select dbo.get_sales_dod() ", nativeQuery=true)
	float sales_DOD();
	
	@Query(value="select ISNULL(sum(budget), 0) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id "
			+ " where month(timestamp) = ?1 and year(timestamp) = year(getdate()) ", nativeQuery=true)
	float salesByMonth(int month);
	
	@Query(value="select ISNULL(sum(budget), 0) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id "
			+ " where year(timestamp) = ?1 ", nativeQuery=true)
	float salesByYear(int year);
	
	@Query(value="select count(*) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id ", nativeQuery=true)
	long purchase();
	
	@Query(value="select dbo.get_purchase_mom() ", nativeQuery=true)
	long purchase_MOM();
	
	@Query(value="select dbo.get_purchase_dod() ", nativeQuery=true)
	long purchase_DOD();
	
	@Query(value="select count(*) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id "
			+ " where month(timestamp) = ?1 and year(timestamp) = year(getdate()) ", nativeQuery=true)
	long purchaseByMonth(int month);
	
	@Query(value="select count(*) from movie_purchase_history "
			+ " join movie on movie_purchase_history.movie = movie.id "
			+ " where year(timestamp) = ?1 ", nativeQuery=true)
	long purchaseByYear(int year);
	
	@Query(value="select movie from movie_purchase_history "
			+ " group by movie "
			+ " order by count(movie) desc ", nativeQuery=true)
	List<Integer> getTopMoviePurchased();
}
