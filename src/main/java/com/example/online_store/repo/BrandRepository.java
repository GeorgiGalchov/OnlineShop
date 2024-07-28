package com.example.online_store.repo;

import java.util.List;

import com.example.online_store.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    //  @Query("SELECT b FROM BrandEntity b "
//      + "JOIN FETCH b.models ")
    @EntityGraph(
            value = "brandWithModels",
            attributePaths = "models"
    )
    @Query("SELECT b FROM BrandEntity b")
    List<BrandEntity> getAllBrands();

}