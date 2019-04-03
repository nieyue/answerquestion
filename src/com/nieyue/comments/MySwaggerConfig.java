package com.nieyue.comments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置swagger
 */
@Configuration
@EnableSwagger2
public class MySwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .title("平台接口文档")
                                .description("平台接口文档1.0版本")
                                .version("1.0")
                                .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nieyue.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
     public RequestMappingInfoHandlerMapping requestMapping(){
            return new RequestMappingHandlerMapping();
        }
}