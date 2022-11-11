package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Account_Role;

@Repository
public interface Account_RoleDAO extends JpaRepository<Account_Role, Integer>{
	
}
