package com.fpoly.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fpoly.spring.key.Movie_GenreKey;
import com.fpoly.spring.key.NotificationKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Movie_Genre]")
public class Movie_Genre implements Serializable{
	@EmbeddedId
	Movie_GenreKey id = new Movie_GenreKey();
	@MapsId("movie_id")
	@ManyToOne @JoinColumn(name="movie")
	private Movie movie;
	@MapsId("genre_id")
	@ManyToOne @JoinColumn(name="genre")
	private Genre genre;
}
