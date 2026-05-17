package com.example.trade_order.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trade_order.dto.TradeOrderDto;
import com.example.trade_order.service.TradeOrderServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class TradeOrderController {
	
	@Autowired
	private TradeOrderServiceImpl serviceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<TradeOrderDto> createOrder(@Valid @RequestBody TradeOrderDto orderDto){
		TradeOrderDto created = serviceImpl.placeOrder(orderDto);
		return ResponseEntity.created(URI.create("/api/orders"+ created.getId())).body(created);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TradeOrderDto> getById(@PathVariable Long id){
		TradeOrderDto order = serviceImpl.getById(id);
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("")
	public ResponseEntity<List<TradeOrderDto>> getAll(){
		List<TradeOrderDto> orders = serviceImpl.getAll();
		return ResponseEntity.ok(orders);
	}

	@PutMapping("update/{id}")
	public ResponseEntity<TradeOrderDto> update(@PathVariable Long id ,@Valid @RequestBody TradeOrderDto orderDto){
		TradeOrderDto updateOrder=serviceImpl.modifyOrder(id,orderDto);
		return ResponseEntity.ok(updateOrder);
	}
	@DeleteMapping("delete/{id}")
	public ResponseEntity<TradeOrderDto> deleteOrder(@PathVariable Long id){
		serviceImpl.cancelOrder(id);
		return ResponseEntity.noContent().build();
	}
}
