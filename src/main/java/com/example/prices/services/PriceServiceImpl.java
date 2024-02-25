package com.example.prices.services;

import com.example.prices.model.Price;
import com.example.prices.repository.PriceRepository;
import com.example.prices.response.PriceResponse;
import com.example.prices.services.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PriceServiceImpl implements PriceService {

    private PriceRepository priceRepository;
    private PriceMapper priceMapper;
    @Override
    public PriceResponse getPrice(LocalDateTime applicationDate, Long productId, int brandId) {
        Optional<Price> price = null;
        priceRepository.findAll();
        List<Price> prices = priceRepository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(applicationDate, applicationDate,
                productId, brandId);
        if(prices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Price not found");
        }
            price = Optional.ofNullable(prices.get(0));
            if (prices.size() != 1) {
                price = prices.stream().max(Comparator.comparingInt(Price::getPriority));
            }


        return priceMapper.priceToPriceResponse(price.orElse(null));
    }
}
