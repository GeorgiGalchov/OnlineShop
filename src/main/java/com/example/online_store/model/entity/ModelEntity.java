package com.example.online_store.model.entity;

import com.example.online_store.model.enums.ModelCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name="models")
public class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ModelCategoryEnum category;

    @ManyToOne
    private BrandEntity brand;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(ModelCategoryEnum category) {
        this.category = category;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }

    @Override
    public String toString() {
        return "ModelEntity{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}