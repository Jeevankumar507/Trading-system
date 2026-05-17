package com.cognizant.tradingCompany.dto;

import lombok.Data;

@Data
public class TradingCompanyResponseDto {
	
	private Long id;

   private String name;
	
	private String licenseNumber;
	
	private String contactEmail;
	
	private String address;
	
	private String createdAt;
}
