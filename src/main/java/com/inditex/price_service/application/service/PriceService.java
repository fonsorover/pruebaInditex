package com.inditex.price_service.application.service;


import com.inditex.price_service.application.port.in.GetPriceUseCase;
import com.inditex.price_service.domain.model.Price;
import com.inditex.price_service.infrastructure.entity.PriceEntity;
import com.inditex.price_service.application.port.out.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price getPriceUseCase(int brandId, int productId, LocalDateTime applicationDate) {
        List<PriceEntity> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                brandId, productId, applicationDate, applicationDate);

        return prices.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getPriority(), p1.getPriority()))
                .findFirst()
                .map(this::toDomain)
                .orElse(null);
    }

    private Price toDomain(PriceEntity entity) {
        return new Price(
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getProductId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurr()
        );
    }
}
