package com.example.online_store.service.impl;

import com.example.online_store.model.entity.UserRoleEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRoleEntityTest {

    @Test
    public void testUserRoleEntity() {

        UserRoleEntity userRoleEntity = new UserRoleEntity();


        userRoleEntity.setId(1L);
        userRoleEntity.setRole(UserRoleEnum.ADMIN);


        assertNotNull(userRoleEntity.getId(), "ID не трябва да бъде null");
        assertEquals(1L, userRoleEntity.getId(), "ID не съвпада");

        assertNotNull(userRoleEntity.getRole(), "Role не трябва да бъде null");
        assertEquals(UserRoleEnum.ADMIN, userRoleEntity.getRole(), "Role не съвпада");
    }
}
