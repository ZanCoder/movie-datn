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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	Double rate = 0.0;
	@ManyToOne @JoinColumn(name="quality")
	private Quality quality;
	String duration_min;
	String poster;
	String cover;
	String description;
	Date release_date;
	@Column(name="add_date", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date add_date = new java.sql.Date(System.currentTimeMillis());
	String productions;
	String casts;
	float budget;
	boolean vip;
	Double imdb_rate = 0.0;
	int views_count = 0;
	int rates_count = 0;
	String trailer;
	@ManyToOne @JoinColumn(name="type")
	private Type type;
	@OneToMany(mappedBy="movie")
	private List<Movie_Genre> movie_genres;
	@OneToMany(mappedBy="movie")
	private List<Movie_Country> movie_countries;
}
