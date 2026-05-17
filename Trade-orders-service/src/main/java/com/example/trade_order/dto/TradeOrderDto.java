package com.example.trade_order.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TradeOrderDto {
	
	private Long id;

	@NotNull(message = "Trader Id is required")
	private Long traderId;
	
	@NotNull(message = "Asset ID is required ")
	private Long assetId;
	
	@NotBlank(message = "Order type must be BUY or SELL ")
	private String orderType;
	
	@DecimalMin(value = "1.0",message = "Quantity must be at least 1 ")
	private BigDecimal quantity;
	
	@DecimalMin(value = "0.01",message = "Price must be greater than 0 ")
	private BigDecimal price;
	
	private String status;
	
	private String createdAt;
	
}
