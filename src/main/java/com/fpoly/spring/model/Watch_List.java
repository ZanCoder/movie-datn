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
@Table(name="[Watch_List]")
public class Watch_List {
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
	String status;
	@Column(name="add_date", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date add_date = new java.sql.Date(System.currentTimeMillis());
	
	@JsonProperty("movie_id")
	public int getMovieId() {
		return this.movie.getId();
	}
	
	@JsonProperty("movie_slug")
	public String getMovieSlug() {
		return this.movie.getSlug();
	}
	
	@JsonProperty("movie_type")
	public String getMovieType() {
		return this.movie.getType().getMovie_type_name();
	}
	
	@JsonProperty("movie_poster")
	public String getMoviePoster() {
		return this.movie.getPoster();
	}
	
	@JsonProperty("movie_title")
	public String getMovieTitle() {
		return this.movie.getTitle();
	}
	
	@JsonProperty("movie_duration_min")
	public String getDurationMin() {
		return this.movie.getDuration_min();
	}
	
	@JsonProperty("movie_vip")
	public Boolean isVip() {
		return this.movie.isVip();
	}
	
	@JsonProperty("movie_quality")
	public String getQuality() {
		return this.movie.getQuality().getQuality_name();
	}
}
