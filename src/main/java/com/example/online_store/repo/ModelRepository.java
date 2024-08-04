package com.example.online_store.repo;

import com.example.online_store.model.entity.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {


    List<ModelEntity> findAllByBrandId (Long id);
}