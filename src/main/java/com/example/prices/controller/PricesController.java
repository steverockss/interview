package com.example.prices.controller;


import com.example.prices.model.Price;
import com.example.prices.response.ErrorResponse;
import com.example.prices.response.PriceResponse;
import com.example.prices.services.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.prices.services.PriceServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/api")
public class PricesController {

    private PriceServiceImpl priceService;


    @Autowired
    public PricesController(PriceServiceImpl priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices")
    public ResponseEntity<?> getPrice(@RequestParam Long productId,
                                      @RequestParam int brandId,
                                      @RequestParam String requestedDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            LocalDateTime requestedLocalDateTime = LocalDateTime.parse(requestedDate, formatter);
            PriceResponse price = priceService.getPrice(requestedLocalDateTime, productId, brandId);

            return ResponseEntity.ok(price);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Precio no encontrado para los parametros ingresados"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse("Error interno del servidor"));
        }
    }

}
