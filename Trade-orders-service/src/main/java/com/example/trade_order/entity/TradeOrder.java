package com.example.trade_order.entity;

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
@Table(name = "trade_order")
public class TradeOrder extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "trader_id",nullable = false )
	private Long traderId;
	
	@Column(name = "asset_id",nullable = false)
	private Long assetId;
	
	@Column(name = "order_type",nullable = false,length = 50)
	private String orderType;
	
	@Column(precision = 14,scale = 2,nullable = false)
	private BigDecimal quantity;
	
    @Column(precision = 12,scale = 2,nullable = false)
	private BigDecimal price;
    
    @Column(length = 50,nullable = false)
	private String status;
	
	
	
}
