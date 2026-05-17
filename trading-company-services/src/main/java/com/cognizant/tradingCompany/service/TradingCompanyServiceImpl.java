package com.cognizant.tradingCompany.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.tradingCompany.dto.TradingCompanyRequestDto;
import com.cognizant.tradingCompany.dto.TradingCompanyResponseDto;
import com.cognizant.tradingCompany.entity.TradingCompany;
import com.cognizant.tradingCompany.exception.ConflictException;
import com.cognizant.tradingCompany.exception.ResourceNotFoundException;
import com.cognizant.tradingCompany.mapper.TradingCompanyMapper;
import com.cognizant.tradingCompany.repository.TradingCompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradingCompanyServiceImpl implements TradingCompanyService {

	@Autowired
	private TradingCompanyRepository repo;

	@Override
	public TradingCompanyResponseDto create(TradingCompanyRequestDto request) {
		boolean exists = repo.existsByLicenseNumber(request.getLicenseNumber());
		if (exists) {
			throw new ConflictException("License number already exists. ");
		}
		TradingCompany entity = TradingCompanyMapper.toEntity(request);
		TradingCompany saved = repo.save(entity);
		return TradingCompanyMapper.toResponseDto(saved);
	}

	@Override
	public TradingCompanyResponseDto getById(Long id) {
		TradingCompany entity = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trading company not found by id: " + id +" not found"));
		return TradingCompanyMapper.toResponseDto(entity);
	}

	@Override
	public List<TradingCompanyResponseDto> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll().stream().map(TradingCompanyMapper::toResponseDto).collect(Collectors.toList());
	}

	@Override
	public TradingCompanyResponseDto update(Long id, TradingCompanyRequestDto request) {
		// TODO Auto-generated method stub
		TradingCompany entity = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trading Company not found with id: " + id));

		entity.setName(request.getName());

		entity.setLicenseNumber(request.getLicenseNumber());
		entity.setContactEmail(request.getContactEmail());
		entity.setAddress(request.getAddress());

		TradingCompany update = repo.save(entity);
		return TradingCompanyMapper.toResponseDto(update);
	}

	@Override
	public void delete(Long id) {
		if (!repo.existsById(id)) {

			throw new ResourceNotFoundException("Trading Company not found with id: " + id);
		}
		repo.deleteById(id);

	}

}
