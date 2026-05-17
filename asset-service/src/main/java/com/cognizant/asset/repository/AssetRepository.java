package com.cognizant.asset.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.asset.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {

	Optional<Asset> findBySymbol(String symbol);
	boolean existsBySymbol(String symbol);
}
