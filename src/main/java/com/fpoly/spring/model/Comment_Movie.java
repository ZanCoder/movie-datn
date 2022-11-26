package com.fpoly.spring.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Comment_Movie]")
public class Comment_Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="account")
	private Account account;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="movie_episode")
	private Movie_Episode movie_episode;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="parent_cmt")
	private Comment_Movie parent_cmt;
	String tag_name;
	String text;
	Boolean spoil;
	@Column(name="timestamp", columnDefinition="DATETIME DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	Date timestamp;
	@OneToMany(mappedBy="parent_cmt")
	@JsonIgnore
	private List<Comment_Movie> comment_movies;
	@OneToMany(mappedBy="comment_movie")
	@JsonIgnore
	private List<Comment_Movie_Detail> comment_movie_details;
	
	@JsonProperty("movie_epId")
	public int getMovieEpId() {
		return this.movie_episode.getId();
	}
	
	@JsonProperty("account_avatar")
	public String getAccountAvatar() {
		return this.account.getAvatar();
	}
	
	@JsonProperty("account_username")
	public String getAccountUsername() {
		return this.account.getUsername();
	}
	
	@JsonProperty("comment_movie_like")
	public long getCommentMovieLike() {
		return this.comment_movie_details == null ? 0 : this.comment_movie_details.stream()
				.filter(o -> o.getFavorite() == true).count();
	}
	
	@JsonProperty("comment_movie_dislike")
	public long getCommentMovieDislike() {
		return this.comment_movie_details == null ? 0 : this.comment_movie_details.stream()
				.filter(o -> o.getFavorite() == false).count();
	}
	
	@JsonProperty("comment_replies")
	public List<Comment_Movie> getReplies() {
		return this.comment_movies == null ? new ArrayList() : this.comment_movies.stream()
				.filter(o -> o.getParent_cmt().getId() == this.id)
				.sorted(Comparator.comparing(Comment_Movie::getTimestamp).reversed())
				.collect(Collectors.toList());
	}
	
	@JsonProperty("comment_reply")
	public Comment_Movie getReplyById(int replyId) {
		return this.comment_movies.stream()
				.filter(o -> o.getId() == replyId)
				.findFirst().get();
	}
}
