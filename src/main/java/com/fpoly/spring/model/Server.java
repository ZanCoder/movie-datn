package com.fpoly.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Server]")
public class Server {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String movie_server_name;
}
