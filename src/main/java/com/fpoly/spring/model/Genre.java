package com.fpoly.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Genre]")
public class Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String genre_name;
	String genre_slug;
	@OneToMany(mappedBy="genre")
	@JsonIgnore
	private List<Movie_Genre> movie_genres;
}
