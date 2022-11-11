package com.fpoly.spring.dao;

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
	@Query(value="insert into coin_transaction_history (coin_value, account) "
			+ " values (?1, ?2) ", nativeQuery=true)
	void saveTransaction(double coin_value, int account);
}
