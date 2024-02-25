package com.example.prices.services.mapper;


import com.example.prices.model.Price;
import com.example.prices.response.PriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {


    public PriceResponse priceToPriceResponse(Price price) {
        if (price == null) {
            return null;
        }
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setPrice(price.getPrice());
        priceResponse.setPriceList(price.getPriceList());
        priceResponse.setEndDate(price.getEndDate());
        priceResponse.setStartDate(price.getStartDate());
        priceResponse.setBrandId(price.getBrandId());

        return priceResponse;

    }
}
