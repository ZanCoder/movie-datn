package com.fpoly.spring.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Movie;
import com.fpoly.spring.model.Notification_Movie;

@Repository
public interface Notification_MovieDAO extends JpaRepository<Notification_Movie, Integer>{
	@Query(value="SELECT o FROM Notification_Movie o WHERE o.account.id = ?1 ORDER BY o.timestamp DESC")
	Page<Notification_Movie> findByAccountOrderByTimestamp(int accountId, Pageable pageable);
	
	@Query(value="SELECT o FROM Notification_Movie o WHERE o.account.id = ?1")
	List<Notification_Movie> findByAccount(int accountId);
	
	@Query(value="SELECT o FROM Notification_Movie o WHERE o.account.id = ?1 and o.new_noti = ?2")
	List<Notification_Movie> findByAccountAndNewNoti(int accountId, boolean new_noti);
	
	@Query(value="SELECT o FROM Notification_Movie o WHERE o.account.id = ?1 and o.new_noti = ?2 ORDER BY o.timestamp DESC")
	Page<Notification_Movie> findByAccountAndNewNotiOrderByTimestamp(int accountId, boolean new_noti, Pageable pageable);
	
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="update notification_movie "
			+ " set new_noti = 0"
			+ " where account = ?1 ", nativeQuery=true)
	void markAll(int account);
}
