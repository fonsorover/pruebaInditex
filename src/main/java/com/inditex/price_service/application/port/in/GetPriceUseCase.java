package com.inditex.price_service.application.port.in;

import com.inditex.price_service.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price getPriceUseCase(int brandId, int productId, LocalDateTime applicationDate);
}
