package com.example.online_store.service.impl;

import com.example.online_store.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ObjectNotFoundExceptionTest {

    @Test
    void testObjectNotFoundExceptionWithMessage() {
        // Arrange
        String expectedMessage = "Object not found";

        // Act
        ObjectNotFoundException exception = new ObjectNotFoundException(expectedMessage);

        // Assert
        assertEquals(expectedMessage, exception.getMessage(), "Exception message should match the expected message");
    }

    @Test
    void testObjectNotFoundExceptionIsRuntimeException() {
        // Arrange
        String message = "Test message";

        // Act
        ObjectNotFoundException exception = new ObjectNotFoundException(message);

        // Assert
        assertThrows(RuntimeException.class, () -> {
            throw exception;
        }, "ObjectNotFoundException should be a RuntimeException");
    }
}
