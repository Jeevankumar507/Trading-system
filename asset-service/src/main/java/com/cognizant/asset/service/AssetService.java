package com.cognizant.asset.service;

import java.util.List;

import com.cognizant.asset.dto.AssetDto;

public interface AssetService {

	AssetDto createAsset(AssetDto dto);
	AssetDto getAsset(Long id);
	List<AssetDto> getAllAssets();
	AssetDto updateAsset(Long id,AssetDto dto);
	void deleteAsset(Long id);
}
