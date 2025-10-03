package kz.proffix4.tsn_todoapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Конфигурация для разрешения CORS-запросов
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Разрешаем CORS для всех эндпоинтов /api/**
        registry.addMapping("/api/**")
                // Разрешаем запросы с любого источника (для разработки)
                .allowedOrigins("*")
                // Разрешаем методы GET, POST, PUT, DELETE, OPTIONS
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Разрешаем все заголовки
                .allowedHeaders("*")
                // Разрешаем куки (если нужно)
                .allowCredentials(false); // Установлено false, так как используем allowedOrigins("*")
    }
}
