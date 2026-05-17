package com.example.trade_order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.trade_order.exception.BadRequestException;
import com.cognizant.trade_order.exception.ResourceNotFoundException;
import com.example.trade_order.client.AssetClient;
import com.example.trade_order.client.TraderClient;
import com.example.trade_order.dto.AssetDto;
import com.example.trade_order.dto.TradeOrderDto;
import com.example.trade_order.dto.TraderDto;
import com.example.trade_order.entity.TradeOrder;
import com.example.trade_order.mapper.TradeOrderMapper;
import com.example.trade_order.repository.TradeOrderRepo;

@Service
public class TradeOrderServiceImpl implements TradeOrderService {

	@Autowired
	private TradeOrderRepo orderRepo;
	
	@Autowired
	private TraderClient traderClient;
	
	@Autowired
	private AssetClient assetClient;
	
	@Override
	public TradeOrderDto placeOrder(TradeOrderDto dto) {
		// TODO Auto-generated method stub
		
		//validate trader
		TraderDto traderDto = traderClient.getByID(dto.getTraderId());
		if(traderDto == null) {
			throw new ResourceNotFoundException("trader not found "+dto.getTraderId());
		}
		
		//validate asset
		
		AssetDto assetDto = assetClient.getAsset(dto.getAssetId());
		if(assetDto == null) {
			throw new ResourceNotFoundException("Assest not found "+dto.getAssetId());
		}
		
		TradeOrder set = TradeOrderMapper.toEntity(dto);
		set.setStatus("OPEN");
		TradeOrder saved = orderRepo.save(set);
		return TradeOrderMapper.toDto(saved);
		
	}

	@Override
	public TradeOrderDto getById(Long id) {
		// TODO Auto-generated method stub
		TradeOrder findId = orderRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("id not found"+id));
		
		return TradeOrderMapper.toDto(findId);
	}

	@Override
	public List<TradeOrderDto> getAll() {
		// TODO Auto-generated method stub
		return orderRepo.findAll().stream().map(TradeOrderMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TradeOrderDto modifyOrder(Long id, TradeOrderDto orderDto) {
		// TODO Auto-generated method stub
		TradeOrder updateOrder = orderRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("order not found with orderId"+id));
		if(!"OPEN".equals(updateOrder.getStatus())) {
			throw new BadRequestException("Only OPEN can modify");
		}
		updateOrder.setQuantity(orderDto.getQuantity());
		updateOrder.setPrice(orderDto.getPrice());
		
		return TradeOrderMapper.toDto(orderRepo.save(updateOrder));
	}

	@Override
	public void cancelOrder(Long id) {
		// TODO Auto-generated method stub
		TradeOrder getId = orderRepo.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Order not found"));
		getId.setStatus("CANCELLED");
		orderRepo.save(getId);

	}

}
