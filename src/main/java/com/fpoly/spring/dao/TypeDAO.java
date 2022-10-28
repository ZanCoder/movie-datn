package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fpoly.spring.model.Type;

@Repository
public interface TypeDAO extends JpaRepository<Type, Integer>{

}
