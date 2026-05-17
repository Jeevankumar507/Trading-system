package com.cognizant.trader.mapper;

import java.time.format.DateTimeFormatter;

import com.cognizant.trader.dto.TraderDto;
import com.cognizant.trader.entity.Trader;

public class TraderMapper {

	public static TraderDto toDto(Trader trader) {
		TraderDto traderDto = new TraderDto();
		traderDto.setId(trader.getId());
		traderDto.setTradingCompanyId(trader.getTradingCompanyId());
		traderDto.setFirstName(trader.getFirstName());
		traderDto.setLastName(trader.getLastName());
		traderDto.setEmail(trader.getEmail());
		traderDto.setPhoneNumber(trader.getPhoneNumber());
		traderDto.setCreatedAt(trader.getCreatedAt() != null
			    ? trader.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME)
			    : null);
	
		return traderDto;
	}
	
	public static Trader toEntity(TraderDto traderDto) {
		Trader trader = new Trader();
		trader.setTradingCompanyId(traderDto.getTradingCompanyId());
		trader.setId(traderDto.getId());
		trader.setTradingCompanyId(traderDto.getTradingCompanyId());
		trader.setFirstName(traderDto.getFirstName());
		trader.setLastName(traderDto.getLastName());
		trader.setEmail(traderDto.getEmail());
		trader.setPhoneNumber(traderDto.getPhoneNumber());
		
		return trader;
	}
}
