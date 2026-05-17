package com.cognizant.tradingCompany.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="trading_companies")
public class TradingCompany extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",length=150, nullable=false)
	private String name;
	
	@Column(name="license_number",length=100, nullable = false)
	private String licenseNumber;
	
	@Column(name = "contact_email",length =100,nullable = false)
	private String contactEmail;
	
	@Column(name="address",length = 250,nullable = false)
	private String address;
	

}
