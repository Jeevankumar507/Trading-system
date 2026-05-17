package com.cognizant.trader.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "traders")
public class Trader extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "trading_company_id",nullable = false)
	private Long tradingCompanyId;
	
	@Column(name = "first_name",nullable = false,length = 100)
	private String firstName;
	
	@Column(name = "last_name",nullable = false,length = 100)
	private String lastName;
	
	@Column(name = "email",nullable = false,length = 100)
	private String email;
	
	@Column(name = "phone_number",nullable = false,length = 20)
	private String phoneNumber;

	
}
