package com.inditex.price_service.controller;

import com.inditex.price_service.infrastructure.entity.PriceEntity;
import com.inditex.price_service.application.port.out.PriceRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp() {
        priceRepository.deleteAll();

        priceRepository.saveAll(List.of(
                new PriceEntity(1, 1, 35455, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 0, new BigDecimal("35.50"), "EUR"),
                new PriceEntity(1, 2, 35455, LocalDateTime.parse("2020-06-14T15:00:00"), LocalDateTime.parse("2020-06-14T18:30:00"), 1, new BigDecimal("25.45"), "EUR"),
                new PriceEntity(1, 3, 35455, LocalDateTime.parse("2020-06-15T00:00:00"), LocalDateTime.parse("2020-06-15T11:00:00"), 1, new BigDecimal("30.50"), "EUR"),
                new PriceEntity(1, 4, 35455, LocalDateTime.parse("2020-06-15T16:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), 1, new BigDecimal("38.95"), "EUR")
        ));
    }

    @Test
    @DisplayName("Prueba 1: Petición a las 10:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test1() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Prueba 2: Petición a las 16:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test2() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    @DisplayName("Prueba 3: Petición a las 21:00 del día 14 para producto 35455 y marca 1 (ZARA)")
    void test3() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Prueba 4: Petición a las 10:00 del día 15 para producto 35455 y marca 1 (ZARA)")
    void test4() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    @DisplayName("Prueba 5: Petición a las 21:00 del día 16 para producto 35455 y marca 1 (ZARA)")
    void test5() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }

}
