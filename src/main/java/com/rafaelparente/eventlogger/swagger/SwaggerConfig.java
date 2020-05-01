package com.rafaelparente.eventlogger.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rafaelparente.eventlogger"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(securityScheme())
                .securityContexts(securityContext());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My Event Logger REST API",
                "Final project for Codenation's AceleraDev Java.",
                "API TOS",
                "Terms of service",
                new Contact("Rafael Parente de Sousa", "https://github.com/rafaelparente", "rafaelp.dev@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[0];
    }

    private List<SecurityScheme> securityScheme() {
        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
                .grantTypes(Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token")))
                .scopes(Arrays.asList(scopes()))
                .build();
        return Arrays.asList(oauth);
    }

    private List<SecurityContext> securityContext() {
        return Arrays.asList(SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
                .forPaths(PathSelectors.ant("/v1/**"))
                .build());
    }

}
