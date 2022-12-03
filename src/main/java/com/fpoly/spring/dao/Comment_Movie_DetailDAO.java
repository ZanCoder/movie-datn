package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Comment_Movie;
import com.fpoly.spring.model.Comment_Movie_Detail;

@Repository
public interface Comment_Movie_DetailDAO extends JpaRepository<Comment_Movie_Detail, Integer>{
	@Query(value="SELECT o FROM Comment_Movie_Detail o "
			+ "WHERE o.account.id = ?1 and o.comment_movie.id = ?2")
	Comment_Movie_Detail findByAccountAndCommentMovie(int account, int cm);
	
	@Query(value="SELECT o FROM Comment_Movie_Detail o "
		+ " WHERE o.comment_movie.id = ?1 ")
	List<Comment_Movie_Detail> findByCommentMovie(int cm);
	
	@Query(value="select count(*) from comment_movie_detail"
			+ " where account = ?1 and comment_movie = ?2 and favorite = 1", nativeQuery=true)
	Integer checkLike(int account, int cm);
	
	@Query(value="select count(*) from comment_movie_detail"
			+ " where account = ?1 and comment_movie = ?2 and favorite = 0", nativeQuery=true)
	Integer checkDisLike(int account, int cm);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update comment_movie_detail "
			+ " set favorite = ?3 "
			+ " where account = ?1 and comment_movie = ?2", nativeQuery=true)
	void updateFavorite(int account, int comment_movie, boolean favorite);
}
