package com.example.online_store.model.dto;

import java.util.List;

public record BrandDTO(String name, List<ModelDTO> models) {
}