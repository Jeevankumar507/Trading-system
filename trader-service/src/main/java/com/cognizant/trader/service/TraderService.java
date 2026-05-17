package com.cognizant.trader.service;

import java.util.List;

import com.cognizant.trader.dto.TraderDto;

public interface TraderService {

	TraderDto create(TraderDto dto);
	TraderDto getById(Long id);
	List<TraderDto> getAll();
	TraderDto update(Long id, TraderDto dto);
	void delete(Long id);
}
