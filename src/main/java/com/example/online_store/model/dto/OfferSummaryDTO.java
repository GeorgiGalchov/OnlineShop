package com.example.online_store.model.dto;

import com.example.online_store.model.enums.EngineEnum;
import com.example.online_store.model.enums.TransmissionEnum;

import java.math.BigDecimal;


public record OfferSummaryDTO(
        String id,
        String brand,
        String model,
        int year,
        int mileage,
        BigDecimal price,
        EngineEnum engine,
        TransmissionEnum transmission,
        String imageUrl
) {
    public String summary() {
        return brand + " " + model + ", " + year;
    }
}