package com.fpoly.spring.form;

public class AdminNotificationMovieForm {
	int send_to;
	int movie_ep;
	String description;
	
	public AdminNotificationMovieForm() {
		super();
	}

	public AdminNotificationMovieForm(int send_to, int movie_ep, String description) {
		super();
		this.send_to = send_to;
		this.movie_ep = movie_ep;
		this.description = description;
	}

	public int getSend_to() {
		return send_to;
	}

	public void setSend_to(int send_to) {
		this.send_to = send_to;
	}

	public int getMovie_ep() {
		return movie_ep;
	}

	public void setMovie_ep(int movie_ep) {
		this.movie_ep = movie_ep;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
