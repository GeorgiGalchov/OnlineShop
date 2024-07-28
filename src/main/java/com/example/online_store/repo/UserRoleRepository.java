package com.example.online_store.repo;

import com.example.online_store.model.entity.UserRoleEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    List<UserRoleEntity> findAllByRoleIn(List<UserRoleEnum> roles);

}