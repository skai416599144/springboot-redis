package com.sk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	 public Docket newsApi() {
		
		Predicate<String> selector = PathSelectors.any();
		Predicate<RequestHandler> apiOption = RequestHandlerSelectors.withClassAnnotation(RestPublish.class);
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
	            .select().paths(selector).apis(apiOption).build()
	            .useDefaultResponseMessages(false);
	 }
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("springboot-redis Service API").description("Provide the springboot-redis test api")
				.termsOfServiceUrl("http://url").version("1.0").build();
	}
}
