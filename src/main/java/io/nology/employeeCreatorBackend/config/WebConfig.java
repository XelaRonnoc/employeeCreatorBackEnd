package io.nology.employeeCreatorBackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry){
		// create an array of allowed addresses
		String[] allowedOrigins = {"http://127.0.0.1:5173/", "http://localhost:5173/", "http://localhost:3000/"}; // add allowed addresses like local host both local host 5173 and local host 3000
		registry.addMapping("/**")
		.allowedOrigins(allowedOrigins)
		.allowedMethods("GET", "POST", "DELETE", "PATCH", "PUT")
		.allowedHeaders("*"); //allow all
	}
}
