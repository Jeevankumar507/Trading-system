package com.example.trade_order.service;

import java.util.List;

import com.example.trade_order.dto.TradeOrderDto;


public interface TradeOrderService {

	TradeOrderDto placeOrder(TradeOrderDto dto);
	TradeOrderDto getById(Long id);
	List<TradeOrderDto> getAll();
	TradeOrderDto modifyOrder(Long id , TradeOrderDto orderDto);
	void cancelOrder(Long id);
	
}
