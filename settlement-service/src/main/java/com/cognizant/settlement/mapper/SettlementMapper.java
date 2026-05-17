package com.cognizant.settlement.mapper;

import java.time.format.DateTimeFormatter;

import com.cognizant.settlement.dto.SettlementDto;
import com.cognizant.settlement.entity.Settlement;

public class SettlementMapper {

	public static SettlementDto toDto(Settlement settlement) {
		SettlementDto dto = new SettlementDto();
		dto.setAmount(settlement.getAmount());
		dto.setId(settlement.getId());
		dto.setPaymentReference(settlement.getPaymentReference());
		dto.setStatus(settlement.getStatus());
		dto.setTradeOrderId(settlement.getTradeOrderId());
		dto.setSettlementDate(settlement.getSettlementDate() != null
			    ? settlement.getSettlementDate().format(DateTimeFormatter.ISO_DATE_TIME)
			    : null);
		return dto;
	}
	
	public static Settlement toEntity(SettlementDto dto) {
		Settlement settlement = new Settlement();
		settlement.setAmount(dto.getAmount());
		settlement.setId(dto.getId());
		settlement.setPaymentReference(dto.getPaymentReference());
		settlement.setStatus(dto.getStatus());
		settlement.setTradeOrderId(dto.getTradeOrderId());
		
		return settlement;
	}
}
