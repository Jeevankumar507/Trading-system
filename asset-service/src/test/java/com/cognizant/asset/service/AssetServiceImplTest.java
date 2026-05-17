package com.cognizant.asset.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import com.cognizant.asset.dto.AssetDto;
import com.cognizant.asset.entity.Asset;
import com.cognizant.asset.exception.ConflictException;
import com.cognizant.asset.exception.ResourceNotFoundException;
import com.cognizant.asset.mapper.AssetMapper;
import com.cognizant.asset.repository.AssetRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AssetServiceImplTest {

    @Mock
    private AssetRepository repository;

    @InjectMocks
    private AssetServiceImpl service;

    private AssetDto assetDto;
    private Asset asset;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        assetDto = new AssetDto(1L, "Apple", "AAPL", "Stock", new BigDecimal("100.00"), LocalDate.of(2020, 1, 1));
        asset = AssetMapper.toEntity(assetDto);
    }

    @Test
    void testCreateAsset_Success() {
        when(repository.existsBySymbol("AAPL")).thenReturn(false);
        when(repository.save(any(Asset.class))).thenReturn(asset);

        AssetDto result = service.createAsset(assetDto);

        assertEquals("AAPL", result.getSymbol());
        verify(repository).save(any(Asset.class));
    }

    @Test
    void testCreateAsset_Conflict() {
        when(repository.existsBySymbol("AAPL")).thenReturn(true);

        assertThrows(ConflictException.class, () -> service.createAsset(assetDto));
    }

    @Test
    void testGetAsset_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(asset));

        AssetDto result = service.getAsset(1L);

        assertEquals("Apple", result.getName());
    }

    @Test
    void testGetAsset_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getAsset(1L));
    }

    @Test
    void testGetAllAssets() {
        when(repository.findAll()).thenReturn(Arrays.asList(asset));

        List<AssetDto> result = service.getAllAssets();

        assertEquals(1, result.size());
        assertEquals("AAPL", result.get(0).getSymbol());
    }

    @Test
    void testUpdateAsset_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(asset));
        when(repository.save(any(Asset.class))).thenReturn(asset);

        assetDto.setName("Updated Apple");
        AssetDto result = service.updateAsset(1L, assetDto);

        assertEquals("Updated Apple", result.getName());
    }

    @Test
    void testUpdateAsset_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.updateAsset(1L, assetDto));
    }

    @Test
    void testDeleteAsset_Success() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteAsset(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteAsset_NotFound() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> service.deleteAsset(1L));
    }
}
