package com.example.online_store.testutils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.example.online_store.model.entity.*;
import com.example.online_store.model.enums.EngineEnum;
import com.example.online_store.model.enums.TransmissionEnum;
import com.example.online_store.repo.BrandRepository;
import com.example.online_store.repo.ExchangeRateRepository;
import com.example.online_store.repo.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestDataUtil {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private BrandRepository brandRepository;

    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRateEntity().setCurrency(currency).setRate(rate)
        );
    }


    public OfferEntity createTestOffer(UserEntity owner) {

        BrandEntity brandEntity = brandRepository.save(new BrandEntity()
                .setName("Test Brand")
                .setModels(List.of(
                        new ModelEntity().setName("Test Model"),
                        new ModelEntity().setName("Test Model1")
                )));

        // create test offer
        OfferEntity offer = new OfferEntity()
                .setModel(brandEntity.getModels().get(0))
                .setImageUrl("https://www.google.com")
                .setPrice(BigDecimal.valueOf(1000))
                .setYear(2020)
                .setUuid(UUID.randomUUID())
                .setDescription("Test Description")
                .setEngine(EngineEnum.PETROL)
                .setMileage(10000)
                .setTransmission(TransmissionEnum.MANUAL)
                .setSeller(owner);

        return offerRepository.save(offer);
    }
    //
    public void cleanUp() {
        exchangeRateRepository.deleteAll();
        offerRepository.deleteAll();
        brandRepository.deleteAll();
    }
}