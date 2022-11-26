package com.fpoly.spring.model;

import java.util.Date;

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
@Table(name="[Movie_Purchase_History]")
public class Movie_Purchase_History {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="account")
	private Account account;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="movie")
	private Movie movie;
	@Column(name="timestamp", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date timestamp = new java.sql.Date(System.currentTimeMillis());
	
	@JsonProperty("movie_title")
	public String getMovieTitle() {
		return this.movie.getTitle();
	}
	
	@JsonProperty("movie_poster")
	public String getMoviePoster() {
		return this.movie.getPoster();
	}
	
	@JsonProperty("movie_budget")
	public float getMovieBudget() {
		return this.movie.getBudget();
	}
	
	@JsonProperty("movie_slug")
	public String getMovieSlug() {
		return this.movie.getSlug();
	}
}
