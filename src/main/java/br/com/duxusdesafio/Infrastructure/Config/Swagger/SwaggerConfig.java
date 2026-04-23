package br.com.duxusdesafio.Infrastructure.Config.Swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Token";

        return new OpenAPI()
                .info(new Info()
                        .title("Duxux Desafio Api")
                        .version("1.0")
                        .description("API do Desafio Duxux"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira o token JWT. Exemplo: eyJhbGciOi...")
                        ));
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner openSwagger() {
        return args -> {
            String url = "http://localhost:8080/swagger-ui/index.html";
            String os = System.getProperty("os.name").toLowerCase();
            try {
                if (os.contains("win")) {
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac")) {
                    Runtime.getRuntime().exec("open " + url);
                } else {
                    Runtime.getRuntime().exec("xdg-open " + url);
                }
            } catch (Exception e) {
                System.out.println("Erro ao abrir Swagger: " + e.getMessage());
            }
        };
    }
}
