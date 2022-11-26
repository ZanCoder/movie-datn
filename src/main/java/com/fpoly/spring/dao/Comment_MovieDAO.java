package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Comment_Movie;

@Repository
public interface Comment_MovieDAO extends JpaRepository<Comment_Movie, Integer>{
	@Query(value="SELECT * FROM [comment_movie] "
			+ " WHERE movie_episode = ?1 AND parent_cmt IS NULL"
			+ " ORDER BY timestamp DESC", nativeQuery=true)
	List<Comment_Movie> findByMovieEpisode(int movie_episode);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into comment_movie (account, movie_episode, text, spoil) "
			+ " values (:#{#cm.account}, :#{#cm.movie_episode}, :#{#cm.text}, :#{#cm.spoil}) ", nativeQuery=true)
	void saveCommentBase(@Param("cm") Comment_Movie comment_movie);
}
