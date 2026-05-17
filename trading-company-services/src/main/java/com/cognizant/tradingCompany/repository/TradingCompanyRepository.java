package com.cognizant.tradingCompany.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.tradingCompany.entity.TradingCompany;

@Repository
public interface TradingCompanyRepository extends JpaRepository<TradingCompany, Long> {
	
	Optional<TradingCompany> findByLicenseNumber(String licenseNumber);
	
	boolean existsByLicenseNumber(String licenseNumber);

}
