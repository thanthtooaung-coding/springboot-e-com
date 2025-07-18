package com.springboot.ecom.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuration for OpenAPI documentation (Swagger).
 * This class sets up the basic information for the API documentation,
 * including server details, API info, and security schemes.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a customized OpenAPI bean.
     * This bean provides metadata for the Swagger UI.
     *
     * @return A configured {@link OpenAPI} object.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        // Define the server URL. You can externalize this to application.properties if needed.
        final Server server = new Server().url("http://localhost:8080").description("Local Development Server");

        // Define the API information.
        final Info info = new Info()
                .title("Product API")
                .version("1.0.0")
                .description("API documentation for the Product application.");

        // Define the Bearer Authentication security scheme.
        final SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // Add the security scheme to the components and add a global security requirement.
        return new OpenAPI()
                .servers(List.of(server))
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme));
    }
}
