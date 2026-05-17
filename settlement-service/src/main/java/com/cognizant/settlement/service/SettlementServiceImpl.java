package com.cognizant.settlement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.settlement.client.TraderOrderClient;
import com.cognizant.settlement.dto.SettlementDto;
import com.cognizant.settlement.dto.TradeOrderDto;
import com.cognizant.settlement.entity.Settlement;
import com.cognizant.settlement.exception.ResourceNotFoundException;
import com.cognizant.settlement.mapper.SettlementMapper;
import com.cognizant.settlement.repository.SettlementRepository;

import feign.FeignException;

@Service
public class SettlementServiceImpl implements SettlementService {

	@Autowired
	private SettlementRepository repository;

	@Autowired
	private TraderOrderClient traderOrderClient;

	@Override
	public SettlementDto createSettlement(SettlementDto dto) {
		// TODO Auto-generated method stub

		// validate trader order
		
		try {
			TradeOrderDto orderDto = traderOrderClient.getById(dto.getTradeOrderId());
		} catch (FeignException.NotFound ex) {
			throw new ResourceNotFoundException("Trade order not found with ID: " + dto.getTradeOrderId());
		}

		/*
		// validate trader order
		TradeOrderDto orderDto = traderOrderClient.getById(dto.getId());
		if (orderDto == null) {
			throw new ResourceNotFoundException("Trade order not found: ");
		}

     */
		Settlement settlement = SettlementMapper.toEntity(dto);

		return SettlementMapper.toDto(repository.save(settlement));
		
		
	}

	@Override
	public SettlementDto getSettlement(Long id) {
		// TODO Auto-generated method stub
		Settlement getSettlement = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Settlement not found " + id));
		return SettlementMapper.toDto(getSettlement);
	}

	@Override
	public List<SettlementDto> getAllSettlement() {
		// TODO Auto-generated method stub
		return repository.findAll().stream().map(SettlementMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public SettlementDto updateSettlement(Long id, SettlementDto dto) {
		// TODO Auto-generated method stub
		Settlement settlement = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Settlement not found wit id: " + id));
		settlement.setTradeOrderId(dto.getTradeOrderId());
		settlement.setAmount(dto.getAmount());
		settlement.setStatus(dto.getStatus());
		settlement.setPaymentReference(dto.getPaymentReference());

		return SettlementMapper.toDto(repository.save(settlement));
	}

	@Override
	public void deleteSettlement(Long id) {
		// TODO Auto-generated method stub

		Settlement settlement = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Settlement not found with id " + id));
		repository.delete(settlement);
	}

}
