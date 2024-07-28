package com.example.online_store.repo;

import com.example.online_store.model.entity.ExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, String> {
}