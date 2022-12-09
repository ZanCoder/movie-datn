package com.fpoly.spring.form;

public class AdminMovieEpisodeForm {
	int movie;
	String title;
	int ep;
	int season;
	String server_hyd;
	String server_sb;
	
	public AdminMovieEpisodeForm() {
		super();
	}

	public AdminMovieEpisodeForm(int movie, String title, int ep, int season, String server_hyd, String server_sb) {
		super();
		this.movie = movie;
		this.title = title;
		this.ep = ep;
		this.season = season;
		this.server_hyd = server_hyd;
		this.server_sb = server_sb;
	}

	public int getMovie() {
		return movie;
	}

	public void setMovie(int movie) {
		this.movie = movie;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEp() {
		return ep;
	}

	public void setEp(int ep) {
		this.ep = ep;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public String getServer_hyd() {
		return server_hyd;
	}

	public void setServer_hyd(String server_hyd) {
		this.server_hyd = server_hyd;
	}

	public String getServer_sb() {
		return server_sb;
	}

	public void setServer_sb(String server_sb) {
		this.server_sb = server_sb;
	}
}
