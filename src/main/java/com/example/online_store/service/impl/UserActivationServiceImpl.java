package com.example.online_store.service.impl;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Random;

import com.example.online_store.model.entity.UserActivationCodeEntity;
import com.example.online_store.model.events.UserRegisteredEvent;
import com.example.online_store.repo.UserActivationCodeRepository;
import com.example.online_store.repo.UserRepository;
import com.example.online_store.service.EmailService;
import com.example.online_store.service.UserActivationService;
import com.example.online_store.service.exception.ObjectNotFoundException;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserActivationServiceImpl implements UserActivationService {

    private static final String ACTIVATION_CODE_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789";
    private static final int ACTIVATION_CODE_LENGTH = 20;
    private final EmailService emailService;
    private final UserRepository userRepository;
    private final UserActivationCodeRepository userActivationCodeRepository;

    public UserActivationServiceImpl(EmailService emailService,
                                     UserRepository userRepository,
                                     UserActivationCodeRepository userActivationCodeRepository) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.userActivationCodeRepository = userActivationCodeRepository;
    }

    @Override
    @EventListener(UserRegisteredEvent.class)
    public void userRegistered(UserRegisteredEvent event) {
        String activationCode = createActivationCode(event.getUserEmail());
        emailService.sendRegistrationEmail(event.getUserEmail(),
                event.getUserNames(),
                activationCode);
    }

    @Override
    public void cleanUpObsoleteActivationLinks() {
        //TODO: Implement
    }

    public String createActivationCode(String userEmail) {

        String activationCode = generateActivationCode();

        UserActivationCodeEntity userActivationCodeEntity = new UserActivationCodeEntity();
        userActivationCodeEntity.setActivationCode(activationCode);
        userActivationCodeEntity.setCreated(Instant.now());
        userActivationCodeEntity.setUser(userRepository.findByEmail(userEmail).orElseThrow(() -> new ObjectNotFoundException("User not found")));

        userActivationCodeRepository.save(userActivationCodeEntity);

        return activationCode;

    }

    private static String generateActivationCode() {

        StringBuilder activationCode = new StringBuilder();
        Random random = new SecureRandom();

        for (int i = 0; i < ACTIVATION_CODE_LENGTH; i++) {
            int randInx = random.nextInt(ACTIVATION_CODE_SYMBOLS.length());
            activationCode.append(ACTIVATION_CODE_SYMBOLS.charAt(randInx));
        }

        return activationCode.toString();
    }


}