package com.capstonedesign.inhalife.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Inhalife")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI matdoriOpenApi() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Inhalife API")
                        .description("슬기로운 인하생활 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}
