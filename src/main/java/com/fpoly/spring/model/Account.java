package com.fpoly.spring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="[Account]")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String username;
	String email;
	String avatar;
	String password_hash;
	Double budget;
	@Column(name="joined_date", columnDefinition="date DEFAULT GETDATE()")
	@Temporal(TemporalType.TIMESTAMP)
	Date joined_date = new java.sql.Date(System.currentTimeMillis());
	@ManyToOne 
	@JsonIgnore
	@JoinColumn(name="role")
	private Account_Role role;
	Boolean status;
	Boolean public_wl;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Coin_Transaction_History> coin_transaction_histories;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Movie_Purchase_History> movie_purchase_hitories;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Movie_Rate> movie_rates;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Continue_Watching> continue_watchings;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Watch_List> watch_lists;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Notification_Movie> notification_movies;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Comment_Movie> comment_movies;
	@OneToMany(mappedBy="account")
	@JsonIgnore
	private List<Comment_Movie_Detail> comment_movie_details;
	
	public Boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, this.password_hash);
    }
}
