package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Quality;

@Repository
public interface QualityDAO extends JpaRepository<Quality, Integer>{

}
