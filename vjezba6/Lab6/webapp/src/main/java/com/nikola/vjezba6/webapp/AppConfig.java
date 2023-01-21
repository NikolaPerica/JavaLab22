package com.nikola.vjezba6.webapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import com.nikola.vjezba6.controller.ErrorExceptionHandler;

@Configuration
@ComponentScan
public class AppConfig {
	@Autowired public SpringTemplateEngine templateEngine;

  
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        final ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setViewClass(ThymeleafView.class);
        resolver.setTemplateEngine(templateEngine);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }
    
    
}
