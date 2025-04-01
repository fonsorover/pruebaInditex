package com.inditex.price_service.infrastructure.controller;


import com.inditex.price_service.application.service.PriceService;
import com.inditex.price_service.domain.model.Price;
import com.inditex.price_service.infrastructure.exception.InvalidDateFormatException;
import com.inditex.price_service.infrastructure.exception.PriceNotFoundException;
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
        // Intentamos parsear la fecha, si no es válida, lanzamos InvalidDateFormatException
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(applicationDate);
        } catch (Exception ex) {
            throw new InvalidDateFormatException("Invalid date format: " + applicationDate);
        }

        // Buscamos el precio aplicable, si no se encuentra, lanzamos PriceNotFoundException
        Price price = priceService.getPriceUseCase(brandId, productId, date);
        if (price == null) {
            throw new PriceNotFoundException("No price found for product " + productId + " on " + applicationDate);
        }

        return price;
    }
}

