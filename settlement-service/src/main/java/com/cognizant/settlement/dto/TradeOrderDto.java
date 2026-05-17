package com.cognizant.settlement.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TradeOrderDto {
	
	private Long id;

	private Long traderId;
	
	private Long assetId;
	
	private String orderType;
	
	private BigDecimal quantity;
	
	private BigDecimal price;
	
	private String status;
	
	private String createdAt;
	
}
