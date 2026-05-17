package com.cognizant.trader.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.trader.entity.Trader;

public interface TraderRepository extends JpaRepository<Trader, Long> {

	boolean existsByEmailAndTradingCompanyId(String email,Long tradingCompanyId);
}
