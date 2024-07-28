package com.example.online_store.repo;

import java.util.Optional;
import java.util.UUID;

import com.example.online_store.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
    Optional<OfferEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}