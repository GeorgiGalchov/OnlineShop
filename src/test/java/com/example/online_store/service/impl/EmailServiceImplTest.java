package com.example.online_store.service.impl;

import com.example.online_store.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailServiceImplTest {

    @Mock
    private TemplateEngine templateEngine;

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendRegistrationEmail_ShouldSendEmailWithCorrectDetails() throws MessagingException {
        // Arrange
        String userEmail = "user@example.com";
        String userName = "John Doe";
        String activationCode = "123456";
        String emailBody = "<html><body>Hello John Doe, your activation code is 123456</body></html>";
        String fromEmail = "noreply@example.com";

        when(templateEngine.process(anyString(), any(Context.class))).thenReturn(emailBody);

        MimeMessage mimeMessage = mock(MimeMessage.class);
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        emailService.sendRegistrationEmail(userEmail, userName, activationCode);

        // Assert
        ArgumentCaptor<MimeMessage> mimeMessageCaptor = ArgumentCaptor.forClass(MimeMessage.class);
        verify(javaMailSender).send(mimeMessageCaptor.capture());

        MimeMessage capturedMessage = mimeMessageCaptor.getValue();

        assertEquals(userEmail, capturedMessage.getRecipients(MimeMessage.RecipientType.TO)[0].toString());
        assertEquals(fromEmail, capturedMessage.getFrom()[0].toString());
        assertEquals("Welcome to online_shop_store!", capturedMessage.getSubject());
        try {
            assertEquals(emailBody, capturedMessage.getContent().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
