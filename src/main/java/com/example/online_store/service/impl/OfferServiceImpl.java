package com.example.online_store.service.impl;

import com.example.online_store.model.dto.CreateOfferDTO;
import com.example.online_store.model.dto.OfferDetailDTO;
import com.example.online_store.model.dto.OfferSummaryDTO;
import com.example.online_store.model.entity.ModelEntity;
import com.example.online_store.model.entity.OfferEntity;
import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.entity.UserRoleEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import com.example.online_store.repo.ModelRepository;
import com.example.online_store.repo.OfferRepository;
import com.example.online_store.repo.UserRepository;
import com.example.online_store.service.MonitoringService;
import com.example.online_store.service.OfferService;
import com.example.online_store.service.aop.WarnIfExecutionExceeds;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final MonitoringService monitoringService;
    private final UserRepository userRepository;

    public OfferServiceImpl(
            OfferRepository offerRepository,
            ModelRepository modelRepository,
            MonitoringService monitoringService,
            UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.monitoringService = monitoringService;
        this.userRepository = userRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDTO createOfferDTO, UserDetails seller) {

        OfferEntity newOffer = map(createOfferDTO);
        ModelEntity modelEntity = modelRepository.findById(createOfferDTO.modelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDTO.modelId() + " not found!"));
        UserEntity sellerEntity = userRepository.findByEmail(seller.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User with email " + seller.getUsername() + " not found!"));


        newOffer.setModel(modelEntity);
        newOffer.setSeller(sellerEntity);

        newOffer = offerRepository.save(newOffer);

        return newOffer.getUuid();
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 1000L
    )
    @Override
    public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {
        return offerRepository
                .findAll(pageable)
                .map(OfferServiceImpl::mapAsSummary);
    }

    @WarnIfExecutionExceeds(
            timeInMillis = 500L
    )
    @Override
    public Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID, UserDetails viewer) {
        return offerRepository
                .findByUuid(offerUUID)
                .map(o -> this.mapAsDetails(o, viewer));
    }

    @Override
    @Transactional
    public void deleteOffer(UUID offerUUID) {
        offerRepository.deleteByUuid(offerUUID);
    }

    private OfferDetailDTO mapAsDetails(OfferEntity offerEntity, UserDetails viewer) {

        return new OfferDetailDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl(),
                offerEntity.getSeller().getFirstName(),
                isOwner(offerEntity, viewer != null ? viewer.getUsername() : null));
    }

    @Override
    public boolean isOwner(UUID uuid, String userName) {
        return isOwner(
                offerRepository.findByUuid(uuid).orElse(null),
                userName
        );
    }

    private boolean isOwner(OfferEntity offerEntity, String userName) {
        if (offerEntity == null || userName == null) {
             return false;
        }

        UserEntity viewerEntity =
                userRepository
                        .findByEmail(userName)
                        .orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

        if (isAdmin(viewerEntity)) {
                  return true;
        }

        return Objects.equals(
                offerEntity.getSeller().getId(),
                viewerEntity.getId());
    }

    private boolean isAdmin(UserEntity userEntity) {
        return userEntity
                .getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(r -> UserRoleEnum.ADMIN == r);
    }


    private static OfferSummaryDTO mapAsSummary(OfferEntity offerEntity) {
        return new OfferSummaryDTO(
                offerEntity.getUuid().toString(),
                offerEntity.getModel().getBrand().getName(),
                offerEntity.getModel().getName(),
                offerEntity.getYear(),
                offerEntity.getMileage(),
                offerEntity.getPrice(),
                offerEntity.getEngine(),
                offerEntity.getTransmission(),
                offerEntity.getImageUrl());
    }

    private static OfferEntity map(CreateOfferDTO createOfferDTO) {
        return new OfferEntity()
                .setUuid(UUID.randomUUID())
                .setDescription(createOfferDTO.description())
                .setEngine(createOfferDTO.engine())
                .setTransmission(createOfferDTO.transmission())
                .setImageUrl(createOfferDTO.imageUrl())
                .setMileage(createOfferDTO.mileage())
                .setPrice(BigDecimal.valueOf(createOfferDTO.price()))
                .setYear(createOfferDTO.year());
    }
}