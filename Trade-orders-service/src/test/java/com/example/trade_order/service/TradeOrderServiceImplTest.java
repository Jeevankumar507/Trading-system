package com.example.trade_order.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.trade_order.dto.TradeOrderDto;
import com.example.trade_order.entity.TradeOrder;
import com.example.trade_order.mapper.TradeOrderMapper;
import com.example.trade_order.repository.TradeOrderRepo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TradeOrderServiceImplTest {

    @Mock
    private TradeOrderRepo orderRepo;

    @InjectMocks
    private TradeOrderServiceImpl service;

    private TradeOrder order;
    private TradeOrderDto dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new TradeOrder();
        order.setId(1L);
        order.setTraderId(100L);
        order.setAssetId(200L);
        order.setOrderType("BUY");
        order.setQuantity(new BigDecimal("10.00"));
        order.setPrice(new BigDecimal("500.00"));
        order.setStatus("OPEN");

        dto = new TradeOrderDto();
        dto.setId(1L);
        dto.setTraderId(100L);
        dto.setAssetId(200L);
        dto.setOrderType("BUY");
        dto.setQuantity(new BigDecimal("10.00"));
        dto.setPrice(new BigDecimal("500.00"));
        dto.setStatus("OPEN");
    }

    @Test
    void testPlaceOrder() {
        when(orderRepo.save(any(TradeOrder.class))).thenReturn(order);

        TradeOrderDto result = service.placeOrder(dto);

        assertEquals("OPEN", result.getStatus());
        verify(orderRepo).save(any(TradeOrder.class));
    }

    @Test
    void testGetById() {
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));

        TradeOrderDto result = service.getById(1L);

        assertEquals(100L, result.getTraderId());
        verify(orderRepo).findById(1L);
    }

    @Test
    void testGetById_NotFound() {
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> service.getById(1L));
        assertTrue(ex.getMessage().contains("id not found"));
    }

    @Test
    void testGetAll() {
        when(orderRepo.findAll()).thenReturn(Arrays.asList(order));

        List<TradeOrderDto> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("BUY", result.get(0).getOrderType());
    }

    @Test
    void testModifyOrder_WhenOpen() {
        TradeOrderDto updatedDto = new TradeOrderDto();
        updatedDto.setQuantity(new BigDecimal("20.00"));
        updatedDto.setPrice(new BigDecimal("600.00"));

        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepo.save(any(TradeOrder.class))).thenReturn(order);

        TradeOrderDto result = service.modifyOrder(1L, updatedDto);

        assertEquals(new BigDecimal("20.00"), result.getQuantity());
        assertEquals(new BigDecimal("600.00"), result.getPrice());
    }

    @Test
    void testModifyOrder_WhenNotOpen() {
        order.setStatus("CANCELLED");
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));

        TradeOrderDto updatedDto = new TradeOrderDto();
        updatedDto.setQuantity(new BigDecimal("20.00"));
        updatedDto.setPrice(new BigDecimal("600.00"));

        Exception ex = assertThrows(IllegalStateException.class, () -> service.modifyOrder(1L, updatedDto));
        assertEquals("Only OPEN can modify", ex.getMessage());
    }

    @Test
    void testCancelOrder() {
        when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepo.save(any(TradeOrder.class))).thenReturn(order);

        service.cancelOrder(1L);

        verify(orderRepo).save(order);
        assertEquals("CANCELLED", order.getStatus());
    }
}
