package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Server;

@Repository
public interface ServerDAO extends JpaRepository<Server, Integer>{

}
