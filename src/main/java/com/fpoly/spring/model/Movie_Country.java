package com.fpoly.spring.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fpoly.spring.key.Movie_CountryKey;
import com.fpoly.spring.key.Movie_GenreKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Movie_Country]")
public class Movie_Country implements Serializable{
	@EmbeddedId
	Movie_CountryKey id = new Movie_CountryKey();
	@MapsId("movie_id")
	@ManyToOne @JoinColumn(name="movie")
	private Movie movie;
	@MapsId("country_id")
	@ManyToOne @JoinColumn(name="country")
	private Country country;
}
