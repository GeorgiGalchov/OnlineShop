package com.example.online_store.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.entity.UserRoleEntity;
import com.example.online_store.model.enums.UserRoleEnum;
import com.example.online_store.repo.UserRepository;

@ExtendWith(MockitoExtension.class)
class OnlineStoreUserDetailsServiceTest {

    private OnlineStoreUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new OnlineStoreUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound() {
        // Arrange
        String email = "test@example.com";
        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> serviceToTest.loadUserByUsername(email));
    }

    @Test
    void testUserFound() {
        // Arrange
        UserEntity testUserEntity = createTestUser();
        when(mockUserRepository.findByEmail(testUserEntity.getEmail())).thenReturn(Optional.of(testUserEntity));

        // Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUserEntity.getEmail());

        // Assert
        assertNotNull(userDetails);
        assertEquals(testUserEntity.getEmail(), userDetails.getUsername(), "Username is not mapped to email.");
        assertEquals(testUserEntity.getPassword(), userDetails.getPassword());
        assertEquals(2, userDetails.getAuthorities().size());
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN), "The user is not admin");
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER), "The user is not user");
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority) {
        return userDetails.getAuthorities().stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static UserEntity createTestUser() {
        return new UserEntity()
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("test@example.com")
                .setActive(true)
                .setPassword("password")
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));
    }
}
