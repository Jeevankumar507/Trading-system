package com.cognizant.tradingCompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
	
	private String statusCode;
	private String statusmessage;
	
	
}
