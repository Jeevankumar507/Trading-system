package com.cognizant.settlement.service;

import java.util.List;

import com.cognizant.settlement.dto.SettlementDto;

public interface SettlementService {

	SettlementDto createSettlement(SettlementDto dto);
	SettlementDto getSettlement(Long id);
	List<SettlementDto> getAllSettlement();
	SettlementDto updateSettlement(Long id,SettlementDto dto);
	void deleteSettlement(Long id);
}
