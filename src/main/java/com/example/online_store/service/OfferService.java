package com.example.online_store.service;

import com.example.online_store.model.dto.CreateOfferDTO;
import com.example.online_store.model.dto.OfferDetailDTO;
import com.example.online_store.model.dto.OfferSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller);

    Page<OfferSummaryDTO> getAllOffers(Pageable pageable);

    Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer);

    void deleteOffer(UUID offerUUID);

    boolean isOwner(UUID uuid, String userName);
}