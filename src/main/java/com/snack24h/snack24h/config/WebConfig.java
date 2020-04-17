package com.snack24h.snack24h.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${office.hostname}")
    private String hostname;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 모든 uri에 대해 해당 도메인은 접근을 허용한다.
        registry.addMapping("/**")
                .allowedOrigins(hostname,
                        "http://localhost:7080",
                        "http://192.168.1.16:7080",
                        "http://127.0.0.1:5500"
                );

    }
}
