package com.springbootfinal.app.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResidConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // /residenceAdd URL을 residenceAdd 뷰와 매핑
        registry.addViewController("/residenceAdd").setViewName("residences/residenceAdd");
        // /residenceWrite URL을 residenceWrite 뷰와 매핑
        registry.addViewController("/residenceWrite").setViewName("residences/residenceAdd");
    }



}
