package com.example.online_store.service.impl;

import com.example.online_store.model.dto.UserRegistrationDTO;
import com.example.online_store.model.entity.UserEntity;
import com.example.online_store.model.events.UserRegisteredEvent;
import com.example.online_store.repo.UserRepository;
import com.example.online_store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher appEventPublisher;
    private final UserDetailsService OnlineStoreUserDetailsService;

    public UserServiceImpl(
            ModelMapper modelMapper, UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ApplicationEventPublisher appEventPublisher,
            UserDetailsService userDetailsService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appEventPublisher = appEventPublisher;
        this.OnlineStoreUserDetailsService = userDetailsService;
    }

    @Override
    public void registerUser(
            UserRegistrationDTO userRegistrationDTO) {

        userRepository.save(map(userRegistrationDTO));

        appEventPublisher.publishEvent(new UserRegisteredEvent(
                "UserService",
                userRegistrationDTO.email(),
                userRegistrationDTO.fullName()
        ));
    }

    @Override
    public void createUserIfNotExist(String email, String names) {
    }

    @Override
    public Authentication login(String email) {
        UserDetails userDetails = OnlineStoreUserDetailsService.loadUserByUsername(email);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return auth;
    }

//        private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
//        return new UserEntity()
//                .setActive(false)
//                .setFirstName(userRegistrationDTO.firstName())
//                .setLastName(userRegistrationDTO.lastName())
//                .setEmail(userRegistrationDTO.email())
//                .setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
//   }

    private UserEntity map(UserRegistrationDTO userRegistrationDTO) {
        UserEntity userEntity = modelMapper.map(userRegistrationDTO, UserEntity.class);
        userEntity.setActive(false);
        userEntity.setFirstName(userRegistrationDTO.firstName());
        userEntity.setLastName(userRegistrationDTO.lastName());
        userEntity.setEmail(userRegistrationDTO.email());
        userEntity.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
        return userEntity;
    }

}