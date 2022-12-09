package com.fpoly.spring.form;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.fpoly.spring.model.Country;
import com.fpoly.spring.model.Genre;

public class AdminMovieForm {
	String title;
	int quality;
	String duration_min;
	String description;
	String productions;
	Date release_date;
	double imdb_rate;
	String trailer;
	int type;
	int status;
	List<Genre> genres;
	List<Country> countries;
	
	public AdminMovieForm() {
		super();
	}

	public AdminMovieForm(String title, int quality, String duration_min, String description, Date release_date,
			String productions, float imdb_rate, String trailer, int type, int status, List<Genre> genres,
			List<Country> countries) {
		super();
		this.title = title;
		this.quality = quality;
		this.duration_min = duration_min;
		this.description = description;
		this.release_date = release_date;
		this.productions = productions;
		this.imdb_rate = imdb_rate;
		this.trailer = trailer;
		this.type = type;
		this.status = status;
		this.genres = genres;
		this.countries = countries;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public String getDuration_min() {
		return duration_min;
	}

	public void setDuration_min(String duration_min) {
		this.duration_min = duration_min;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	public String getProductions() {
		return productions;
	}

	public void setProductions(String productions) {
		this.productions = productions;
	}

	public double getImdb_rate() {
		return imdb_rate;
	}

	public void setImdb_rate(double imdb_rate) {
		this.imdb_rate = imdb_rate;
	}

	public String getTrailer() {
		return trailer;
	}

	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}

	public List<Country> getCountries() {
		return countries;
	}

	public void setCountries(List<Country> countries) {
		this.countries = countries;
	}
}
