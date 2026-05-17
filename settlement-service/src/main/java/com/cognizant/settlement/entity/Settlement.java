package com.cognizant.settlement.entity;

import java.math.BigDecimal;

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
@Table(name = "settlements")
public class Settlement extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="trade_order_id ",nullable = false)
	private Long tradeOrderId;
	
	@Column(precision = 14,scale = 2,nullable = false)
	private BigDecimal amount;
	
	@Column(nullable = false,length = 50)
	private String status;
	
	@Column(name="payment_reference",nullable = false,length = 100)
	private String paymentReference;
}
