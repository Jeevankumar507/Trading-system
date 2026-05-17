package com.example.trade_order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trade_order.entity.TradeOrder;

public interface TradeOrderRepo extends JpaRepository<TradeOrder, Long> {

	List<TradeOrder> findByTraderId(Long traderId);
	List<TradeOrder> findByStatus(String status);
	
}
