package com.fpoly.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Continue_Watching;
import com.fpoly.spring.model.Watch_List;

@Repository
public interface Watch_ListDAO extends JpaRepository<Watch_List, Integer>{
	@Query("SELECT o FROM Watch_List o "
			+ " WHERE o.account.id = ?1 "
			+ " AND o.status like ?2 ")
	Page<Watch_List> findByAccountAndStatusLike(int account, String status, Pageable pageable);
	
	@Query("SELECT o FROM Watch_List o "
			+ " WHERE o.account.id = ?1 "
			+ " AND o.status like ?2 "
			+ " ORDER BY o.add_date DESC ")
	Page<Watch_List> findByAccountAndStatusLikeOrderByAddDate(int account, String status, Pageable pageable);
	
	@Query(value="select * from watch_list "
			+ " where account = ?1 "
			+ " and status like ?2  "
			+ " order by dbo.get_movie_rated(movie) desc ", nativeQuery=true)
	Page<Watch_List> findByAccountAndStatusLikeOrderByScore(int account, String status, Pageable pageable);
	
	@Query("SELECT o FROM Watch_List o "
			+ " WHERE o.account.id = ?1 "
			+ " AND o.status like ?2 "
			+ " ORDER BY o.movie.title ASC ")
	Page<Watch_List> findByAccountAndStatusLikeOrderByMovieTitle(int account, String status, Pageable pageable);
	
	@Query("SELECT o FROM Watch_List o "
			+ " WHERE o.account.id = ?1 "
			+ " AND o.status like ?2 "
			+ " ORDER BY o.movie.release_date DESC ")
	Page<Watch_List> findByAccountAndStatusLikeOrderByReleaseDate(int account, String status, Pageable pageable);
	
	@Query(value="select * from watch_list "
			+ " where account = ?1 "
			+ " and status like ?2  "
			+ " order by dbo.get_movie_viewed(movie) desc ", nativeQuery=true)
	Page<Watch_List> findByAccountAndStatusLikeOrderByMostWatched(int account, String status, Pageable pageable);
	
	@Query("SELECT o FROM Watch_List o "
			+ " WHERE o.account.id = ?1 "
			+ " AND o.movie.id = ?2 ")
	Watch_List findByAccountAndMovie(int account, int movie);
}
