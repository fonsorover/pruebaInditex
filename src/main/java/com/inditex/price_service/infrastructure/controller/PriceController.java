package com.inditex.price_service.infrastructure.controller;


import com.inditex.price_service.application.service.PriceService;
import com.inditex.price_service.domain.model.Price;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public Price getPrice(
            @RequestParam int brandId,
            @RequestParam int productId,
            @RequestParam String applicationDate
    ) {
        LocalDateTime date;
        date = LocalDateTime.parse(applicationDate);
        Price price = priceService.getPriceUseCase(brandId, productId, date);
        return price;
    }
}

