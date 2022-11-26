package com.fpoly.spring.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Movie_Episode;

@Embeddable
public class NotificationKey implements Serializable{
	@Column(name="account")
    private int account_id;
	
    @Column(name="movie_episode")
    private int movie_episode_id;
}
