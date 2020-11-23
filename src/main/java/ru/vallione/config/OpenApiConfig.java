package ru.vallione.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@EnableWebSecurity
public class OpenApiConfig {

    @Bean
    public OpenAPI customConfiguration(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Client API Docs")
                        .description("Простая REST API документация"));
    }

}
