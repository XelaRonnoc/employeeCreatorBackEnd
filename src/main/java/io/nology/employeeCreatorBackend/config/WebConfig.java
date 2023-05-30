package io.nology.employeeCreatorBackend.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	
	public void addCorsMapping(CorsRegistry registry){
		// create an array of allowed addresses
		String[] allowedOrigins = {"",""}; // add allowed addresses like local host both local host 5173 and local host 3000
		registry.addMapping("/**")
		.allowedOrigins(allowedOrigins)
		.allowedMethods("GET", "POST", "DELETE")
		.allowedHeaders("*"); //allow all
	}
}
