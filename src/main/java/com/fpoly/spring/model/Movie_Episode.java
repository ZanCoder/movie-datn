package com.fpoly.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Movie_Episode]")
public class Movie_Episode {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name="movie")
	private Movie movie;
	String title;
	int ep;
	int season;
	@ManyToOne @JoinColumn(name="server")
	private Server server;
	String api_key;
	@OneToMany(mappedBy="movie_episode")
	@JsonIgnore
	private List<Continue_Watching> continue_watchings;
	@OneToMany(mappedBy="movie_episode")
	@JsonIgnore
	private List<Notification_Movie> notification_movies;
	@OneToMany(mappedBy="movie_episode")
	@JsonIgnore
	private List<Comment_Movie> comment_movies;
}
