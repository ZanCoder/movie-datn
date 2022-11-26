package com.fpoly.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="[Continue_Watching]")
public class Continue_Watching {
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
	@Column(name="view_date", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date view_date = new java.sql.Date(System.currentTimeMillis());
	
	@JsonProperty("movie_id")
	public int getMovieId() {
		return this.movie_episode.getMovie().getId();
	}
	
	@JsonProperty("movie_slug")
	public String getMovieSlug() {
		return this.movie_episode.getMovie().getSlug();
	}
	
	@JsonProperty("season")
	public int getSeason() {
		return this.movie_episode.getSeason();
	}
	
	@JsonProperty("type")
	public int getType() {
		return this.movie_episode.getMovie().getType().getId();
	}
	
	@JsonProperty("ep")
	public int getEp() {
		return this.movie_episode.getEp();
	}
	
	@JsonProperty("movie_poster")
	public String getMoviePoster() {
		return this.movie_episode.getMovie().getPoster();
	}
	
	@JsonProperty("movie_title")
	public String getMovieTitle() {
		return this.movie_episode.getMovie().getTitle();
	}
	
	@JsonProperty("movie_type")
	public String getMovieType() {
		return this.movie_episode.getMovie().getType().getMovie_type_name();
	}
	
	@JsonProperty("movie_quality")
	public String getQuality() {
		return this.movie_episode.getMovie().getQuality().getQuality_name();
	}
}
