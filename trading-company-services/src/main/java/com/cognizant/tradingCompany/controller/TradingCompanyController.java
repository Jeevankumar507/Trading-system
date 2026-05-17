package com.cognizant.tradingCompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.tradingCompany.dto.TradingCompanyRequestDto;
import com.cognizant.tradingCompany.dto.TradingCompanyResponseDto;
import com.cognizant.tradingCompany.service.TradingCompanyServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies")
public class TradingCompanyController {

	@Autowired
	private TradingCompanyServiceImpl serviceImpl;
	
	@PostMapping("/create")
	public ResponseEntity<TradingCompanyResponseDto> create(@Valid @RequestBody TradingCompanyRequestDto requestDto){
		TradingCompanyResponseDto createdResponse=serviceImpl.create(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TradingCompanyResponseDto> getById(@PathVariable Long id){
		TradingCompanyResponseDto getCompany=serviceImpl.getById(id);
		return ResponseEntity.ok(getCompany);
	}
	

	 @GetMapping("")
	    public ResponseEntity<List<TradingCompanyResponseDto>> getAll() {
	        List<TradingCompanyResponseDto> companies = serviceImpl.getAll();
	        return ResponseEntity.ok(companies); 
	    }


    @PutMapping("/update/{id}")
    public ResponseEntity<TradingCompanyResponseDto> update(@PathVariable Long id,@Valid
                                                             @RequestBody TradingCompanyRequestDto requestDto) {
        TradingCompanyResponseDto updatedDto = serviceImpl.update(id, requestDto);
        return ResponseEntity.ok(updatedDto); 
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	serviceImpl.delete(id);
        return ResponseEntity.noContent().build(); 
    }

}
