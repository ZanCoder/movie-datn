package com.fpoly.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Movie_Genre]")
public class Movie_Genre {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	@ManyToOne @JoinColumn(name="movie")
	private Movie movie;
	@ManyToOne @JoinColumn(name="genre")
	private Genre genre;
}
