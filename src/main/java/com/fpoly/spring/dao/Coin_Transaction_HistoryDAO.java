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
	
	@Query(value="select ISNULL(sum(coin_value), 0) from coin_transaction_history", nativeQuery=true)
	float revenue();
	
	@Query(value="select count(*) from coin_transaction_history where card like ?1 ", nativeQuery=true)
	long tranfer(String card);
	
	@Query(value="select * from coin_transaction_history order by timestamp desc ", nativeQuery=true)
	List<Coin_Transaction_History> findAllOrderByTimeStampDesc();
	
	@Query(value="select dbo.get_revenue_mom()", nativeQuery=true)
	float revenue_MOM();
	
	@Query(value="select dbo.get_revenue_dod()", nativeQuery=true)
	float revenue_DOD();
	
	@Query(value="select ISNULL(sum(coin_value), 0) from coin_transaction_history "
			+ " where month(timestamp) = ?1 and year(timestamp) = year(getdate()) ", nativeQuery=true)
	float revenueByMonth(int month);
	
	@Query(value="select ISNULL(sum(coin_value), 0) from coin_transaction_history "
			+ " where year(timestamp) = ?1 ", nativeQuery=true)
	float revenueByYear(int year);
}
