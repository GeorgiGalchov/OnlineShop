package com.example.online_store.testutils;

import java.util.List;

import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import com.example.online_store.repo.UserRepository;
import com.example.online_store.repo.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTestDataUtil {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    public UserEntity createTestUser(String email) {
        return createUser(email, List.of(UserRoleEnum.USER));
    }

    public UserEntity createTestAdmin(String email) {
        return createUser(email, List.of(UserRoleEnum.ADMIN));
    }

    private UserEntity createUser(String email, List<UserRoleEnum> roles) {

        var roleEntities = userRoleRepository.findAllByRoleIn(roles);

        UserEntity newUser = new UserEntity()
                .setActive(true)
                .setEmail(email)
                .setFirstName("Test user first")
                .setLastName("Test user last")
                .setRoles(
                        roleEntities
                );

        return userRepository.save(newUser);
    }

    public void cleanUp() {
        userRepository.deleteAll();
    }

}