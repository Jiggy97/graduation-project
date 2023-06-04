package com.example.graduationproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String resourcePath = "/upload/**"; // view 에서 접근할 경로 ( 아래 경로의 변수 느낌 )
    private String savePath = "file:///Users/jiggy-ahn/Desktop/springboot_img/"; // 실제 저장 경로

    private String modelResourcePath = "/modelUpload/**";

    private String modelSavePath = "file:///Users/jiggy-ahn/Desktop/face_ML_model/"; // 실제 저장 경로

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(resourcePath)
                .addResourceLocations(savePath);
    }
}
