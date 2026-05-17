package com.cognizant.trader.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.trader.client.TradingCompanyClient;
import com.cognizant.trader.dto.TraderDto;
import com.cognizant.trader.dto.TradingCompanyResponseDto;
import com.cognizant.trader.entity.Trader;
import com.cognizant.trader.exception.ConflictException;
import com.cognizant.trader.exception.ResourceNotFoundException;
import com.cognizant.trader.mapper.TraderMapper;
import com.cognizant.trader.repository.TraderRepository;

@Service
public class TraderServiceImp implements TraderService {

	@Autowired
	private TraderRepository repository;
	
	@Autowired
	private TradingCompanyClient tradingCompanyClient;
	
	@Override
	public TraderDto create(TraderDto dto) {
		
		// validate company using feign
		TradingCompanyResponseDto company = tradingCompanyClient.getById(dto.getTradingCompanyId());
		if(company == null) {
			throw new ResourceNotFoundException("Trading company not found: " +dto.getTradingCompanyId());
		}
		// till here feign is impl
		
		boolean exists=repository.existsByEmailAndTradingCompanyId(dto.getEmail(),dto.getTradingCompanyId());
		if(exists) {
			throw new ConflictException("already exists");
		}
		Trader entity=TraderMapper.toEntity(dto);
		Trader saved =repository.save(entity);
		
		return TraderMapper.toDto(saved);
	}

	@Override
	public TraderDto getById(Long id) {
		// TODO Auto-generated method stub
		Trader findId = repository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("not found id: "+id));
		return TraderMapper.toDto(findId);
	}

	@Override
	public List<TraderDto> getAll() {
		
		return repository.findAll().stream().map(TraderMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TraderDto update(Long id, TraderDto dto) {
		// TODO Auto-generated method stub
		Trader updateTrader = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("trader not found with id: "+ id));
		updateTrader.setFirstName(dto.getFirstName());
		updateTrader.setLastName(dto.getLastName());
		updateTrader.setEmail(dto.getEmail());
		updateTrader.setPhoneNumber(dto.getPhoneNumber());
		
		Trader saveUpdated = repository.save(updateTrader);
		
		return TraderMapper.toDto(saveUpdated);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	if(!repository.existsById(id)) {
		throw new ResourceNotFoundException("Trading company not found with id : "+id);
	}
	repository.deleteById(id);

	}

}
