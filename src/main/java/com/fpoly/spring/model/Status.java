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
@Table(name="[Status]")
public class Status {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String status_name;
	@OneToMany(mappedBy="status")
	private List<Movie> movies;
}