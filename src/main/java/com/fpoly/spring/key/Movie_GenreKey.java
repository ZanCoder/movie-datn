package com.fpoly.spring.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Movie_GenreKey implements Serializable{
	@Column(name="movie")
    private int movie_id;
	
    @Column(name="genre")
    private int genre_id;
}
