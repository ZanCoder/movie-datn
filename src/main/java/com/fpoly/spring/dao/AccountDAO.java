package com.fpoly.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
	@Query(value="SELECT o FROM Account o WHERE o.username = ?1")
	Account findByUsername(String username);
	
	@Query(value="SELECT o FROM Account o WHERE o.email = ?1")
	Account findByEmail(String email);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into account (username, email, password_hash) "
			+ " values (:#{#account.username}, :#{#account.email}, :#{#account.password_hash}) ", nativeQuery=true)
	void createAccount(@Param("account") Account account);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update account "
			+ " set password_hash = ?2 "
			+ " where id = ?1 ", nativeQuery=true)
	void changePassword(int id, String password);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update account "
			+ " set budget = ?2 "
			+ " where id = ?1 ", nativeQuery=true)
	void rechargeCoin(int id, double budget);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update account "
			+ " set username = ?2, password_hash = ?3, avatar = ?4 "
			+ " where id = ?1 ", nativeQuery=true)
	void updateProfile(int id, String username, String password_hash, String avatar);

	@Query(value="select count(*) from account"
			+ " where ?1 like (select username from account where id <> ?2)", nativeQuery=true)
	Integer checkDuplicateUsername(String username, int id);
}
