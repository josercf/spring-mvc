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


}

