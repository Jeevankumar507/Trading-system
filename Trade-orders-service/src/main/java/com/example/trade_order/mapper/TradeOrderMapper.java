package com.example.trade_order.mapper;

import java.time.format.DateTimeFormatter;

import com.example.trade_order.dto.TradeOrderDto;
import com.example.trade_order.entity.TradeOrder;

public class TradeOrderMapper {

	public static TradeOrderDto toDto(TradeOrder order) {
		TradeOrderDto dto = new TradeOrderDto();
		dto.setId(order.getId());
		dto.setTraderId(order.getTraderId());
		dto.setAssetId(order.getAssetId());
		dto.setOrderType(order.getOrderType());
		dto.setQuantity(order.getQuantity());
		dto.setPrice(order.getPrice());
		dto.setStatus(order.getStatus());
		dto.setCreatedAt(order.getCreatedAt() != null
			    ? order.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME)
			    : null);
		return dto;
	}
	
	public static TradeOrder toEntity(TradeOrderDto dto) {
		TradeOrder order=new TradeOrder();
		order.setId(dto.getId());
		order.setTraderId(dto.getTraderId());
		order.setAssetId(dto.getAssetId());
		order.setOrderType(dto.getOrderType());
		order.setQuantity(dto.getQuantity());
		order.setPrice(dto.getPrice());
		order.setStatus(dto.getStatus());
		return order;
	}
}
