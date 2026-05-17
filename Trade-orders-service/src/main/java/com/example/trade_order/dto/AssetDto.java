package com.example.trade_order.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

   
	private Long id;
	
	private String name;
	
	private String symbol;
	
	private String type;
	
	private BigDecimal currentPrice;
	
	private LocalDate listedSince;
}
