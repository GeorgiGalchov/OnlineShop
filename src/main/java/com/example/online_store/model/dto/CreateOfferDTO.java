package com.example.online_store.model.dto;

import com.example.online_store.model.enums.EngineEnum;
import com.example.online_store.model.enums.TransmissionEnum;
import com.example.online_store.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateOfferDTO(@NotEmpty @Size(min = 5, max = 512) String description,
                             @Positive @NotNull Long modelId, @NotNull EngineEnum engine,
                             @NotNull TransmissionEnum transmission, @NotEmpty String imageUrl,
                             @Positive @NotNull Integer mileage,
                             @Positive @NotNull Integer price,
                             @YearNotInTheFuture(message = "The year should not be in the future!")
                             @NotNull(message = "Year must be provided!")
                             @Min(1930)
                             Integer year) {

    public static CreateOfferDTO empty() {

        return new CreateOfferDTO(null, null, null, null, null, null, null, null);
    }
}