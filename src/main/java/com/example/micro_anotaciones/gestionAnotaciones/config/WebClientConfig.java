package com.example.micro_anotaciones.gestionAnotaciones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient estudiantesWebClient() {
        return WebClient.builder().baseUrl("http://localhost:5002/api").build();
    }
}