package com.cognizant.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.asset.dto.AssetDto;
import com.cognizant.asset.service.AssetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/assets")
@Validated
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/create")
    public ResponseEntity<AssetDto> createAsset(@Valid @RequestBody AssetDto assetDto) {
        AssetDto created = assetService.createAsset(assetDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> fetchAsset(@PathVariable Long id) {
        AssetDto asset = assetService.getAsset(id);
        return ResponseEntity.ok(asset);
    }

    @GetMapping("")
    public ResponseEntity<List<AssetDto>> getAllAssets() {
        List<AssetDto> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<AssetDto> updateAsset(@PathVariable Long id,@Valid @RequestBody AssetDto assetDto) {
        AssetDto updated = assetService.updateAsset(id, assetDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
