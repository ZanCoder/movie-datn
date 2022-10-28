package com.fpoly.spring.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	@OneToMany(mappedBy="country")
	private List<Movie_Country> movie_countries;
}
