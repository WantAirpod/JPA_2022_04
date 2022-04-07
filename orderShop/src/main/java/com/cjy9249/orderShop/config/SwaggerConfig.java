package com.cjy9249.orderShop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(swaggerInfo());
    }

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("soosoo v1 API Documentation")
                .description("soosoo v1 API 의 API문서 입니다")
                .license("github").licenseUrl("https://github.com/soosooinc/soosoo_v1_api.git")
                .version("0.1.0")
                .build();
    }
}
