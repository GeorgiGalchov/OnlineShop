package com.example.online_store;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@OpenAPIDefinition(
		info = @Info(
				title = "11-Cars",
				version = "0.0.1",
				description = "The REST API of 11-Cars"
		),
		servers = {
				@Server(
						url = "http://localhost:8080",
						description = "Local server"
				)
		}
)
@EnableScheduling
@EnableCaching
@SpringBootApplication
public class OnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}

}