package br.com.er.votacaoapi.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("votacao-public")
                .packagesToScan("br.com.er.votacaoapi.controller")
                .build();
    }

    @Bean
    public OpenAPI votacaoOpenApi() {
        return new OpenAPI().info(new Info()
                        .title("Votação API")
                        .description("Api para gerenciar votações de associados em pautas.")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }

}
