package com.example.online_store.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
        name = "brandWithModels",
        attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "brand"
    )
    private List<ModelEntity> models;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}