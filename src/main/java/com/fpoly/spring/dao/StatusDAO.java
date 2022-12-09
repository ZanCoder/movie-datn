package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fpoly.spring.model.Status;

@Repository
public interface StatusDAO extends JpaRepository<Status, Integer>{

}
