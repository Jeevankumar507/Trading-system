package com.example.trade_order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.trade_order.dto.TraderDto;



@FeignClient(name = "trader-service" ,url = "http://localhost:8082")
public interface TraderClient {
	
	@GetMapping("/api/traders/{id}")
	public TraderDto getByID(@PathVariable Long id);

}
