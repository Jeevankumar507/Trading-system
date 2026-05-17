package com.cognizant.trader.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.trader.dto.TradingCompanyResponseDto;

@FeignClient(name = "trading-company" ,url = "http://localhost:8081") //---service name from eureka
public interface TradingCompanyClient {

	@GetMapping("/api/companies/{id}")
	public TradingCompanyResponseDto getById(@PathVariable Long id);
}
