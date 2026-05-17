package com.cognizant.settlement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.cognizant.settlement.client.TraderOrderClient;
import com.cognizant.settlement.dto.SettlementDto;
import com.cognizant.settlement.dto.TradeOrderDto;
import com.cognizant.settlement.entity.Settlement;
import com.cognizant.settlement.exception.ResourceNotFoundException;
import com.cognizant.settlement.repository.SettlementRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SettlementServiceImplTest {

    @Mock
    private SettlementRepository repository;

    @Mock
    private TraderOrderClient traderOrderClient;

    @InjectMocks
    private SettlementServiceImpl service;

    private Settlement settlement;
    private SettlementDto dto;
    private TradeOrderDto tradeOrderDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        settlement = new Settlement();
        settlement.setId(1L);
        settlement.setTradeOrderId(101L);
        settlement.setAmount(new BigDecimal("1500.50"));
        settlement.setStatus("COMPLETED");
        settlement.setPaymentReference("REF123");

        dto = new SettlementDto();
        dto.setId(1L);
        dto.setTradeOrderId(101L);
        dto.setAmount(new BigDecimal("1500.50"));
        dto.setStatus("COMPLETED");
        dto.setPaymentReference("REF123");

        tradeOrderDto = new TradeOrderDto(); 
    }

    @Test
    void testCreateSettlement() {
        when(traderOrderClient.getById(dto.getTradeOrderId())).thenReturn(tradeOrderDto);
        when(repository.save(any(Settlement.class))).thenReturn(settlement);

        SettlementDto result = service.createSettlement(dto);

        assertEquals(dto.getTradeOrderId(), result.getTradeOrderId());
        assertEquals(dto.getAmount(), result.getAmount());
        verify(repository).save(any(Settlement.class));
    }

    @Test
    void testGetSettlement() {
        when(repository.findById(1L)).thenReturn(Optional.of(settlement));

        SettlementDto result = service.getSettlement(1L);

        assertEquals(dto.getStatus(), result.getStatus());
        verify(repository).findById(1L);
    }

    @Test
    void testGetAllSettlement() {
        when(repository.findAll()).thenReturn(Arrays.asList(settlement));

        List<SettlementDto> result = service.getAllSettlement();

        assertEquals(1, result.size());
        assertEquals("COMPLETED", result.get(0).getStatus());
    }

    @Test
    void testUpdateSettlement() {
        SettlementDto updatedDto = new SettlementDto();
        updatedDto.setTradeOrderId(102L);
        updatedDto.setAmount(new BigDecimal("2000.00"));
        updatedDto.setStatus("PENDING");
        updatedDto.setPaymentReference("REF456");

        when(repository.findById(1L)).thenReturn(Optional.of(settlement));
        when(repository.save(any(Settlement.class))).thenReturn(settlement);

        SettlementDto result = service.updateSettlement(1L, updatedDto);

        assertEquals("PENDING", result.getStatus());
        assertEquals(new BigDecimal("2000.00"), result.getAmount());
    }

    @Test
    void testDeleteSettlement() {
        when(repository.findById(1L)).thenReturn(Optional.of(settlement));

        service.deleteSettlement(1L);

        verify(repository).delete(settlement);
    }

    @Test
    void testGetSettlement_NotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.getSettlement(1L));
    }
}
