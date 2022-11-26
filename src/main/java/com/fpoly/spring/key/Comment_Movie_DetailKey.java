package com.fpoly.spring.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Comment_Movie_DetailKey implements Serializable{
	@Column(name="account")
    private int account_id;
	
    @Column(name="comment_movie")
    private int comment_movie_id;
}
