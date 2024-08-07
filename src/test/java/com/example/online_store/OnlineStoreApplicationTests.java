package com.example.online_store;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OnlineStoreApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
	void testApplicationStartup() {

		assertThat(true).isTrue(); // Просто пример
	}
}
