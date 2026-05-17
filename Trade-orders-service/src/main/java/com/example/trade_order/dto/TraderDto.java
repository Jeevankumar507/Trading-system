package com.example.trade_order.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TraderDto {

	private Long id;

	private Long tradingCompanyId;

	private String firstName;

	private String lastName;

	private String email;

	private String phoneNumber;

	private String createdAt;
}
