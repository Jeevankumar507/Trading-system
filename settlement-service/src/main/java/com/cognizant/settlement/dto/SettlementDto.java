package com.cognizant.settlement.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementDto {

	private Long id;
	
	private Long tradeOrderId;
	
	private String settlementDate;
	
	private BigDecimal amount;
	
	private String status;
	
	private String paymentReference;	
	
}
