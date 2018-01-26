package com.informed.main;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main entry point for Spring boot micro-service application
 * 
 * @author cruddasj
 * @SpringBootApplication Instructs Spring that service is a spring application
 * @EnableSwagger2 Instructs Spring to provide Swagger documentation for
 *                 application 
 * @ComponentScan("com.informed") Instructs Spring
 *                 to scan all com.informed packages for DI purposes
 * @EnableAutoConfiguration Automatically configure Spring application based on
 *                          jar dependencies
 */
@SpringBootApplication
@ComponentScan("com.informed")
@EnableSwagger2
@EnableCaching
public class RestServiceApplication {

	/**
	 * Main entry method.
	 * 
	 * @param args
	 *            Default args string array
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}

	/**
	 * Method for setting default resource served on index path.
	 * 
	 * @return Redirect to static resource or service location
	 */
	@Bean
	public WebMvcConfigurerAdapter indexRedirect() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.addViewController("/").setViewName("redirect:/swagger-ui.html");
			}
		};
	}

	/**
	 * Method for binding Spring request handlers to Swagger and likewise
	 * managing Swagger configurations.
	 */
	@Bean
	public Docket swaggerApiConsumerDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("consumer")
				.useDefaultResponseMessages(false).select().paths(Predicates.not(regex("/secure/.*")))
				.paths(Predicates.not(regex("/error"))).apis(RequestHandlerSelectors.any()).build()
				.forCodeGeneration(true);
	}

	/**
	 * Method for binding Spring request handlers to Swagger and likewise
	 * managing Swagger configurations.
	 */
	@Bean
	public Docket swaggerApiAdministratorDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("management")
				.useDefaultResponseMessages(false).select().paths(regex("/secure/.*"))
				.apis(RequestHandlerSelectors.any()).build().forCodeGeneration(true);
	}

	/**
	 * Private contact definition for use in API information displayed in the
	 * Swagger interface.
	 */
	private Contact contact = new Contact("name", "web address", "email address");

	/**
	 * Private method for setting API information displayed in the Swagger
	 * interface.
	 * 
	 * @return API information to be displayed in the Swagger interface.
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Custom REST API", "Description of REST API.", "1.0.0", null, contact, null, null);
		return apiInfo;
	}
}
