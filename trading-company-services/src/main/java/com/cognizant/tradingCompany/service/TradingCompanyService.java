package com.cognizant.tradingCompany.service;

import java.util.List;

import com.cognizant.tradingCompany.dto.TradingCompanyRequestDto;
import com.cognizant.tradingCompany.dto.TradingCompanyResponseDto;

public interface TradingCompanyService {

	TradingCompanyResponseDto create(TradingCompanyRequestDto request);
	TradingCompanyResponseDto getById(Long id);
	List<TradingCompanyResponseDto> getAll();
	TradingCompanyResponseDto update(Long id,TradingCompanyRequestDto request);
	void delete(Long id);
}
