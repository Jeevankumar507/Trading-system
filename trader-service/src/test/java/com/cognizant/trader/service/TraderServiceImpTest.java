package com.cognizant.trader.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cognizant.trader.dto.TraderDto;
import com.cognizant.trader.entity.Trader;
import com.cognizant.trader.mapper.TraderMapper;
import com.cognizant.trader.repository.TraderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TraderServiceImpTest {

    @Mock
    private TraderRepository repository;

    @InjectMocks
    private TraderServiceImp service;

    private Trader trader;
    private TraderDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trader = new Trader();
        trader.setId(1L);
        trader.setTradingCompanyId(101L);
        trader.setFirstName("Hrithik");
        trader.setLastName("Kumar");
        trader.setEmail("hrithik.kumar@gmail.com");
        trader.setPhoneNumber("9876543210");

        dto = new TraderDto();
        dto.setId(1L);
        dto.setTradingCompanyId(101L);
        dto.setFirstName("Hrithik");
        dto.setLastName("Kumar");
        dto.setEmail("hrithik.kumar@gmail.com");
        dto.setPhoneNumber("9876543210");
    }

    @Test
    void testCreateTrader_WhenNotExists() {
        when(repository.existsByEmailAndTradingCompanyId(dto.getEmail(), dto.getTradingCompanyId())).thenReturn(false);
        when(repository.save(any(Trader.class))).thenReturn(trader);

        TraderDto result = service.create(dto);

        assertEquals("Hrithik", result.getFirstName());
        verify(repository).save(any(Trader.class));
    }

    @Test
    void testCreateTrader_WhenExists() {
        when(repository.existsByEmailAndTradingCompanyId(dto.getEmail(), dto.getTradingCompanyId())).thenReturn(true);

        Exception ex = assertThrows(RuntimeException.class, () -> service.create(dto));
        assertEquals("already exists", ex.getMessage());
    }

    @Test
    void testGetById_WhenExists() {
        when(repository.findById(1L)).thenReturn(Optional.of(trader));

        TraderDto result = service.getById(1L);

        assertEquals("Hrithik", result.getFirstName());
    }

    @Test
    void testGetById_WhenNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> service.getById(1L));
        assertTrue(ex.getMessage().contains("not found id"));
    }

    @Test
    void testGetAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(trader));

        List<TraderDto> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Hrithik", result.get(0).getFirstName());
    }

    @Test
    void testUpdateTrader() {
        TraderDto updatedDto = new TraderDto();
        updatedDto.setFirstName("Rohit");
        updatedDto.setLastName("Sharma");
        updatedDto.setEmail("rohit.sharma@example.com");
        updatedDto.setPhoneNumber("9999999999");

        when(repository.findById(1L)).thenReturn(Optional.of(trader));
        when(repository.save(any(Trader.class))).thenReturn(trader);

        TraderDto result = service.update(1L, updatedDto);

        assertEquals("Rohit", result.getFirstName());
        assertEquals("Sharma", result.getLastName());
    }

    @Test
    void testDeleteTrader_WhenExists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void testDeleteTrader_WhenNotExists() {
        when(repository.existsById(1L)).thenReturn(false);

        Exception ex = assertThrows(RuntimeException.class, () -> service.delete(1L));
        assertTrue(ex.getMessage().contains("Trading company not found"));
    }
}
