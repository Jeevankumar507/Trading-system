package com.cognizant.trader.controller;

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

import com.cognizant.trader.dto.TraderDto;
import com.cognizant.trader.service.TraderServiceImp;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/traders")
public class TraderController {

	@Autowired
	private TraderServiceImp serviceImp ;
	
	@PostMapping("/create")
	public ResponseEntity<TraderDto> create(@Valid @RequestBody TraderDto traderDto){
		TraderDto created = serviceImp.create(traderDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TraderDto> getByID(@PathVariable Long id){
		TraderDto getTrader = serviceImp.getById(id);
		return ResponseEntity.ok(getTrader);
	}
	
	@GetMapping("")
	public ResponseEntity<List<TraderDto>> getAll(){
		List<TraderDto> findAllTrader = serviceImp.getAll();
		return ResponseEntity.ok(findAllTrader);
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<TraderDto> update(@PathVariable Long id ,@Valid @RequestBody TraderDto traderDto){
		TraderDto updateTrader = serviceImp.update(id, traderDto);
		return ResponseEntity.ok(updateTrader);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<TraderDto> deleteTrader(@PathVariable Long id){
		serviceImp.delete(id);
		return ResponseEntity.noContent().build();
	}
}


// /api/traders/{id}-get  /api/traders?companyId={id}-get   /api/traders/{id}--put   /api/traders/{id} -de
