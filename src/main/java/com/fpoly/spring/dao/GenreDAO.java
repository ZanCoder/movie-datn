package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Genre;

@Repository
public interface GenreDAO extends JpaRepository<Genre, Integer>{

}
