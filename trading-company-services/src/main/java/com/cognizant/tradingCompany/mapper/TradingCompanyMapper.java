package com.cognizant.tradingCompany.mapper;

import java.time.format.DateTimeFormatter;

import com.cognizant.tradingCompany.dto.TradingCompanyRequestDto;
import com.cognizant.tradingCompany.dto.TradingCompanyResponseDto;
import com.cognizant.tradingCompany.entity.TradingCompany;

public class TradingCompanyMapper {

	// Entity → Response DTO

	public static TradingCompanyResponseDto toResponseDto(TradingCompany entity) {
		TradingCompanyResponseDto dto = new TradingCompanyResponseDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setLicenseNumber(entity.getLicenseNumber());
		dto.setContactEmail(entity.getContactEmail());
		dto.setAddress(entity.getAddress());
		dto.setCreatedAt(entity.getCreatedAt() != null
			    ? entity.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME)
			    : null);

		return dto;

	}

	// Request DTO → Entity

	public static TradingCompany toEntity(TradingCompanyRequestDto dto) {
		TradingCompany entity = new TradingCompany();
		entity.setName(dto.getName());
		entity.setLicenseNumber(dto.getLicenseNumber());
		entity.setContactEmail(dto.getContactEmail());
		entity.setAddress(dto.getAddress());
		return entity;
	}

}
