package com.example.prices.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class PriceResponse {


    private int brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private int priceList;

    private Long productId;

    private double price;

}
