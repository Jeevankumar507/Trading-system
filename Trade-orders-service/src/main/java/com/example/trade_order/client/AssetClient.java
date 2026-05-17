package com.example.trade_order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.trade_order.dto.AssetDto;


@FeignClient(name = "asset",url = "http://localhost:8085")
public interface AssetClient {

	 @GetMapping("/api/assets/{id}")
	    public AssetDto getAsset(@PathVariable Long id);
}
