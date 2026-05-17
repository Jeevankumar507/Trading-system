package com.cognizant.tradingCompany.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cognizant.tradingCompany.dto.TradingCompanyRequestDto;
import com.cognizant.tradingCompany.dto.TradingCompanyResponseDto;
import com.cognizant.tradingCompany.entity.TradingCompany;
import com.cognizant.tradingCompany.mapper.TradingCompanyMapper;
import com.cognizant.tradingCompany.repository.TradingCompanyRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TradingCompanyServiceImplTest {

    @Mock
    private TradingCompanyRepository repo;

    @InjectMocks
    private TradingCompanyServiceImpl service;

    private TradingCompany company;
    private TradingCompanyRequestDto requestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        company = new TradingCompany();
        company.setId(1L);
        company.setName("CTS Capital");
        company.setLicenseNumber("LIC123");
        company.setContactEmail("contact@ctscapital.com");
        company.setAddress("Hyderabad, TS");

        requestDto = new TradingCompanyRequestDto();
        requestDto.setName("CTS Capital");
        requestDto.setLicenseNumber("LIC123");
        requestDto.setContactEmail("contact@ctscapital.com");
        requestDto.setAddress("Hyderabad, TS");
    }

    @Test
    void testCreate_WhenLicenseNotExists() {
        when(repo.existsByLicenseNumber("LIC123")).thenReturn(false);
        when(repo.save(any(TradingCompany.class))).thenReturn(company);

        TradingCompanyResponseDto result = service.create(requestDto);

        assertEquals("CTS Capital", result.getName());
        verify(repo).save(any(TradingCompany.class));
    }

    @Test
    void testCreate_WhenLicenseExists() {
        when(repo.existsByLicenseNumber("LIC123")).thenReturn(true);

        Exception ex = assertThrows(RuntimeException.class, () -> service.create(requestDto));
        assertEquals("License number already exists. ", ex.getMessage());
    }

    @Test
    void testGetById_WhenExists() {
        when(repo.findById(1L)).thenReturn(Optional.of(company));

        TradingCompanyResponseDto result = service.getById(1L);

        assertEquals("CTS Capital", result.getName());
    }

    @Test
    void testGetById_WhenNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> service.getById(1L));
        assertTrue(ex.getMessage().contains("Trading company not found"));
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(Arrays.asList(company));

        List<TradingCompanyResponseDto> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("CTS Capital", result.get(0).getName());
    }

    @Test
    void testUpdate_WhenExists() {
        TradingCompanyRequestDto updatedDto = new TradingCompanyRequestDto();
        updatedDto.setName("CTS Global");
        updatedDto.setLicenseNumber("LIC456");
        updatedDto.setContactEmail("info@ctsglobal.com");
        updatedDto.setAddress("Bangalore, KA");

        when(repo.findById(1L)).thenReturn(Optional.of(company));
        when(repo.save(any(TradingCompany.class))).thenReturn(company);

        TradingCompanyResponseDto result = service.update(1L, updatedDto);

        assertEquals("CTS Global", result.getName());
        assertEquals("LIC456", result.getLicenseNumber());
    }

    @Test
    void testDelete_WhenExists() {
        when(repo.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repo).deleteById(1L);
    }

    @Test
    void testDelete_WhenNotExists() {
        when(repo.existsById(1L)).thenReturn(false);

        Exception ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertTrue(ex.getMessage().contains("Trading Company not found"));
    }
}
