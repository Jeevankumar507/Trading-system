package com.cognizant.asset.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="assets")
public class Asset {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 150,nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false)
	private String symbol;
	
	@Column(length = 50,nullable = false)
	private String type;
	
	@Column(name = "current_price",precision = 12,scale = 2,nullable = false)
	private BigDecimal currentPrice;
	
	@Column(name="listed_since",nullable = false)
	private LocalDate listedSince;
}
