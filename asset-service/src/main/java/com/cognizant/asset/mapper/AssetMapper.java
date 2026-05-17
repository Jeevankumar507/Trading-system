package com.cognizant.asset.mapper;

import com.cognizant.asset.dto.AssetDto;
import com.cognizant.asset.entity.Asset;

public class AssetMapper {

	public static AssetDto toDto(Asset asset) {
		AssetDto assetDto = new AssetDto();
		assetDto.setCurrentPrice(asset.getCurrentPrice());
		assetDto.setId(asset.getId());
		assetDto.setListedSince(asset.getListedSince());
		assetDto.setName(asset.getName());
		assetDto.setSymbol(asset.getSymbol());
		assetDto.setType(asset.getType());
		
		return assetDto;
	}
	
	public static Asset toEntity(AssetDto assetDto) {
		Asset asset = new Asset();
		asset.setCurrentPrice(assetDto.getCurrentPrice());
		asset.setId(assetDto.getId());
		asset.setListedSince(assetDto.getListedSince());
		asset.setName(assetDto.getName());
		asset.setSymbol(assetDto.getSymbol());
		asset.setType(assetDto.getType());
		
		return asset;
	}
}
