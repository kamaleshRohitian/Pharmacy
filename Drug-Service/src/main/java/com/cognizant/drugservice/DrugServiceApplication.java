package com.cognizant.drugservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2 
public class DrugServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DrugServiceApplication.class, args);
	}
	@Configuration
	public class SpringFoxConfig {                                    
	    @SuppressWarnings("deprecation")
		@Bean
	    public Docket api() { 
	        return new Docket(DocumentationType.SWAGGER_2)  
	          .select()                                  
	          .apis(RequestHandlerSelectors.any())              
	          .paths(PathSelectors.any())
	          .build()
	          .apiInfo(new ApiInfo("Drug API Documentation", "All Drugs Api are here","1.0.0", "", "Praveen/Kamal/Uma/Nivi", "Drug product", ""));                                           
	    }
	}
}
