package com.cognizant.settlement.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cognizant.settlement.dto.TradeOrderDto;

@FeignClient(name = "trade-orders" ,url = "http://localhost:8083")
public interface TraderOrderClient {

	@GetMapping("/api/orders/{id}")
	public TradeOrderDto getById(@PathVariable Long id);
}
