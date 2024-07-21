package com.example.online_store.service.schedulers;

import com.example.online_store.service.UserActivationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ActivationLinkCleanupScheduler {

    private final UserActivationService userActivationService;

    public ActivationLinkCleanupScheduler(UserActivationService userActivationService) {
        this.userActivationService = userActivationService;
    }

    @Scheduled(fixedRate = 10_000)
    public void cleanUp() {

        userActivationService.cleanUpObsoleteActivationLinks();
    }
}