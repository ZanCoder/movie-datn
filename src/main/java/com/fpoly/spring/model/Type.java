package com.fpoly.spring.model;

import java.util.Date;
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
@Table(name="[Type]")
public class Type {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String movie_type_name;
	String type_slug;
	@OneToMany(mappedBy="type")
	private List<Movie> movies;
}
