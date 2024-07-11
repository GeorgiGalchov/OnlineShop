package com.example.online_store.service;

import com.example.online_store.model.dto.ConvertRequestDTO;
import com.example.online_store.model.dto.ExchangeRatesDTO;
import com.example.online_store.model.dto.MoneyDTO;

public interface CurrencyService {

    void refreshRates(ExchangeRatesDTO exchangeRatesDTO);

    MoneyDTO convert(ConvertRequestDTO convertRequestDTO);
}