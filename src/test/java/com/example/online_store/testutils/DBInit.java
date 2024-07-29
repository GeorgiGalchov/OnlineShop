package com.example.online_store.testutils;

import java.util.List;

import com.example.online_store.model.entity.UserRoleEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import com.example.online_store.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (userRoleRepository.count() == 0) {
            userRoleRepository.saveAll(List.of(
                    new UserRoleEntity().setRole(UserRoleEnum.USER),
                    new UserRoleEntity().setRole(UserRoleEnum.ADMIN)
            ));
        }
    }
}