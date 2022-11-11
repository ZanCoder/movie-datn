package com.fpoly.spring.model;

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
@Table(name="[Country]")
public class Country {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String country_name;
	String country_slug;
	@OneToMany(mappedBy="country")
	@JsonIgnore
	private List<Movie_Country> movie_countries;
}
