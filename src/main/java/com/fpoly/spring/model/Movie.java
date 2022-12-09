package com.fpoly.spring.model;

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
@Table(name="[Movie]")
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String title;
	String slug;
	double rate = 0.0;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="quality")
	private Quality quality;
	String duration_min;
	String poster = "https://iili.io/HCZX6ss.png";
	String cover = "https://iili.io/HCZX4Xn.png";
	String description;
	Date release_date;
	@Column(name="add_date", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date add_date = new java.sql.Date(System.currentTimeMillis());
	String productions;
	float budget;
	boolean vip;
	Double imdb_rate = 0.0;
	String trailer;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="type")
	private Type type;
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="status")
	private Status status;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Movie_Genre> movie_genres;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Movie_Country> movie_countries;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Movie_Purchase_History> movie_purchase_histories;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Movie_Rate> movie_rates;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Movie_View> movie_views;
	@OneToMany(mappedBy="movie")
	@JsonIgnore
	private List<Watch_List> watch_lists;
	
	@JsonProperty("type")
	public String getTypeName() {
		return this.type.getMovie_type_name();
	}
	
	@JsonProperty("status")
	public String getStatusName() {
		return this.status.getStatus_name();
	}
	
	@JsonProperty("genres")
	public List<Genre> getGenres() {
		return this.movie_genres.stream().map(o -> o.getGenre()).collect(Collectors.toList());
	}
	
	@JsonProperty("countries")
	public List<Country> getCountries() {
		return this.movie_countries.stream().map(o -> o.getCountry()).collect(Collectors.toList());
	}
}
