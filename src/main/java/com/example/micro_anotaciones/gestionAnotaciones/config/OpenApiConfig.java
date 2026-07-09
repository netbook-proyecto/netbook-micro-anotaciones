package com.example.micro_anotaciones.gestionAnotaciones.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${app.name:API de Gestión de Anotaciones - netBOOK}")
    private String appName;

    @Value("${app.version:1.0.0}")
    private String appVersion;

    @Bean
    public OpenAPI netbookOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description("Microservicio encargado del registro y administración de anotaciones disciplinarias y de mérito para la plataforma escolar netBOOK.")
                        .version(appVersion))
                .addServersItem(new Server()
                        .url("http://localhost:5005") 
                        .description("Servidor Local (Microservicio Anotaciones)")); 
    }
}