package com.example.prices.services;

import com.example.prices.response.PriceResponse;

import java.time.LocalDateTime;

public interface PriceService {

     PriceResponse getPrice(LocalDateTime applicationDate, Long productId, int brandId);
}
