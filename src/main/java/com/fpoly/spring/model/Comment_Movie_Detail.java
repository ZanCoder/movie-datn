package com.fpoly.spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fpoly.spring.key.Comment_Movie_DetailKey;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Comment_Movie_Detail]")
public class Comment_Movie_Detail implements Serializable{
	@EmbeddedId
	Comment_Movie_DetailKey id = new Comment_Movie_DetailKey();
	@MapsId("account_id")
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="account")
	private Account account;
	@MapsId("comment_movie_id")
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="comment_movie")
	private Comment_Movie comment_movie;
	Boolean favorite;
}
