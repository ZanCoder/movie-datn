package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Continue_Watching;

@Repository
public interface Continue_WatchingDAO extends JpaRepository<Continue_Watching, Integer>{
	@Query("SELECT o FROM Continue_Watching o WHERE o.account.id = ?1 ORDER BY o.view_date DESC")
	Page<Continue_Watching> findByAccountOrderByDesc(int account, Pageable pageable);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into continue_watching (account, movie_episode) "
			+ " values (?1, ?2) ", nativeQuery=true)
	void saveCw(int account, int movie_episode);
	
	@Query(value="SELECT o FROM Continue_Watching o WHERE o.account.id = ?1 AND o.movie_episode.movie.id = ?2")
	Continue_Watching findByAccountAndMovie(int account, int movieId);
	
	@Query(value="SELECT o FROM Continue_Watching o WHERE o.account.id = ?1 AND o.movie_episode.id = ?2")
	Continue_Watching findByAccountAndMovieEpisode(int account, int movieEpId);
}
