package com.cognizant.asset.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.asset.dto.AssetDto;
import com.cognizant.asset.entity.Asset;
import com.cognizant.asset.exception.ConflictException;
import com.cognizant.asset.exception.ResourceNotFoundException;
import com.cognizant.asset.mapper.AssetMapper;
import com.cognizant.asset.repository.AssetRepository;

import jakarta.persistence.Id;

@Service
public class AssetServiceImpl implements AssetService {

	@Autowired
	private AssetRepository repository;

	@Override
	public AssetDto createAsset(AssetDto dto) {
		// TODO Auto-generated method stub
		if (repository.existsBySymbol(dto.getSymbol())) {
			throw new ConflictException("Asset with symbol "+ dto.getSymbol() +" already exists");
		}

		Asset asset = AssetMapper.toEntity(dto);
		Asset saved = repository.save(asset);

		return AssetMapper.toDto(saved);
	}

	@Override
	public AssetDto getAsset(Long id) {
		// TODO Auto-generated method stub

		Asset asset = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Asset not found with Id: " + id));
		return AssetMapper.toDto(asset);

	}

	@Override
	public List<AssetDto> getAllAssets() {
		// TODO Auto-generated method stub

		return repository.findAll().stream().map(AssetMapper::toDto).collect(Collectors.toList());

	}

	@Override
	public AssetDto updateAsset(Long id, AssetDto dto) {
		// TODO Auto-generated method stub

		Asset existing = repository.findById(id).orElseThrow(() 
				-> new ResourceNotFoundException("Asset not found"));

		existing.setName(dto.getName());
		existing.setSymbol(dto.getSymbol());
		existing.setType(dto.getType());
		existing.setCurrentPrice(dto.getCurrentPrice());
		existing.setListedSince(dto.getListedSince());

		Asset updated = repository.save(existing);
		return AssetMapper.toDto(updated);

	}

	@Override
	public void deleteAsset(Long id) {

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Asset not found with ID: "+id);
		}
		repository.deleteById(id);

	}

}
