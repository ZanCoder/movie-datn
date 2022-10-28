package com.fpoly.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="[Quality]")
public class Quality {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String quality_name;
	@OneToMany(mappedBy="quality")
	private List<Movie> movies;
}
