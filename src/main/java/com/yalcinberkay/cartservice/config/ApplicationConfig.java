package com.yalcinberkay.cartservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@Configuration
@EnableSwagger2
public class ApplicationConfig {

    private static final String TITLE = "Cart Api";
    private static final Contact CONTACT = new Contact("Cart Service", "", "email@example.com");

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    @Primary
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yalcinberkay"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("")
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(TITLE, "", "v1", applicationName, CONTACT, "", "", List.of());
    }
}