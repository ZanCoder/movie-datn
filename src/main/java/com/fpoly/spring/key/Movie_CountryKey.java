package com.fpoly.spring.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Movie_CountryKey implements Serializable{
	@Column(name="movie")
    private int movie_id;
	
    @Column(name="country")
    private int country_id;
}
