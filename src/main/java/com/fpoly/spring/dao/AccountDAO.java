package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Account;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{
	@Query(value="SELECT o FROM Account o ORDER BY o.joined_date DESC")
	Page<Account> findAllOrderByJoinedDate(Pageable pagaable);
	
	@Query(value="SELECT o FROM Account o WHERE o.username like ?1 ORDER BY o.joined_date DESC")
	Page<Account> findAllLikeUsernameOrderByJoinedDate(String username, Pageable pagaable);
	
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
			+ " set power = ?2 "
			+ " where id = ?1 ", nativeQuery=true)
	void updatePower(int id, double power);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update account "
			+ " set username = ?2, password_hash = ?3, avatar = ?4 "
			+ " where id = ?1 ", nativeQuery=true)
	void updateProfile(int id, String username, String password_hash, String avatar);

	@Query(value="select count(*) from account"
			+ " where username like ?1 and id <> ?2", nativeQuery=true)
	Integer checkDuplicateUsernameExceptID(String username, int id);
	
	@Query(value="select count(*) from account"
			+ " where username like ?1", nativeQuery=true)
	Integer checkDuplicateUsername(String username);
	
	@Query(value="select count(*) from account"
			+ " where email like ?1", nativeQuery=true)
	Integer checkDuplicateEmail(String email);
	
	@Query(value="select count(*) from account"
			+ " where email like ?1 and id <> ?2", nativeQuery=true)
	Integer checkDuplicateEmailExceptID(String email, int id);
	
	@Query(value="select * from [account]"
			+ " where role = 3 "
			+ " order by power desc ", nativeQuery=true)
	List<Account> getMostPower();
	
	@Query(value="select dbo.get_achievement(?1)", nativeQuery=true)
	String getAchievement(int account);
	
	@Query(value="select * from account where dbo.get_achievement(account.id) = ?1", nativeQuery=true)
	List<Account> getAllByAchievement(String achievement);
}
