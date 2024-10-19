package com.tourismmanagementbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("com.tourismmanagementbackend.dao")
public class TourismManagementBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TourismManagementBackendApplication.class, args);
    }

    @Configuration
    public class WebConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            // 使用绝对路径，并确保路径格式正确
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:D:/xiangmu/tourism-management-backend/src/main/resources/static/uploads/");
        }
    }
}
