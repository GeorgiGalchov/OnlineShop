package com.example.online_store.model.dto;

import java.math.BigDecimal;

public record MoneyDTO(String currency, BigDecimal amount) {
}