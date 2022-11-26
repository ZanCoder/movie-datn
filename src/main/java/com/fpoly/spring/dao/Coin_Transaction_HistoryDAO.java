package com.fpoly.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fpoly.spring.model.Account;
import com.fpoly.spring.model.Coin_Transaction_History;

@Repository
public interface Coin_Transaction_HistoryDAO extends JpaRepository<Coin_Transaction_History, Integer>{
	@Modifying(clearAutomatically=true)
    @Transactional
	@Query(value="insert into coin_transaction_history (coin_value, account, card) "
			+ " values (?1, ?2, ?3) ", nativeQuery=true)
	void saveTransaction(double coin_value, int account, String card);
	
	@Query("SELECT o FROM Coin_Transaction_History o WHERE o.account.id = ?1 ORDER BY o.timestamp DESC")
	Page<Coin_Transaction_History> findByAccountOrderByTimeStampDesc(int account, Pageable pageable);
}
