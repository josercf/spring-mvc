package com.fiap.myapp.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configurable 
@EnableWebMvc 
public class WebConfig implements WebMvcConfigurer { 
    // Configurações personalizadas 

	@Configuration
	public class CorsConfig implements WebMvcConfigurer {
	 
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedOrigins("https://dominio-exemplo.com")
	                .allowedMethods("GET", "POST", "PUT", "DELETE")
	                .allowedHeaders("Content-Type", "Authorization")
	                .allowCredentials(true)
	                .maxAge(3600);
	    }
	}
}

