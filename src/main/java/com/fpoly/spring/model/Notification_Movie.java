package com.fpoly.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fpoly.spring.key.NotificationKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Notification_Movie]")
public class Notification_Movie implements Serializable{
	@EmbeddedId
	NotificationKey id;
	@MapsId("account_id")
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="account")
	private Account account;
	@MapsId("movie_episode_id")
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="movie_episode")
	private Movie_Episode movie_episode;
	Boolean new_noti;
	String description;
	@Column(name="timestamp", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date timestamp = new java.sql.Date(System.currentTimeMillis());
	
	@JsonProperty("movie_title")
	public String getMovieTitle() {
		return this.movie_episode.getMovie().getTitle();
	}
	
	@JsonProperty("movie_poster")
	public String getMoviePoster() {
		return this.movie_episode.getMovie().getPoster();
	}
	
	@JsonProperty("movie_ep")
	public int getMovieEp() {
		return this.movie_episode.getEp();
	}
	
	@JsonProperty("movie_slug")
	public String getMovieSlug() {
		return this.movie_episode.getMovie().getSlug();
	}
	
	@JsonProperty("movie_season")
	public int getMovieSeason() {
		return this.movie_episode.getSeason();
	}
}
