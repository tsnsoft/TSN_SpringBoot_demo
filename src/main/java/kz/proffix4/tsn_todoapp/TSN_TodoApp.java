package kz.proffix4.tsn_todoapp;

import kz.proffix4.tsn_todoapp_client.TodoClient;
import org.springframework.boot.SpringApplication; // Импорт класса для запуска Spring Boot приложения
import org.springframework.boot.autoconfigure.SpringBootApplication; // Импорт аннотации для настройки приложения
import org.springframework.context.ConfigurableApplicationContext;

// Аннотация @SpringBootApplication включает три основные функции:
// 1. @Configuration — определяет класс как источник конфигурации Spring
// 2. @EnableAutoConfiguration — включает автоматическую настройку Spring Boot
// 3. @ComponentScan — сканирует пакеты для поиска компонентов (контроллеров, сервисов и т.д.)
@SpringBootApplication
public class TSN_TodoApp {

    // Главный метод, точка входа в приложение
    public static void main(String[] args) {
        // Запускает приложение Spring Boot, передавая класс TSN_TodoApp и аргументы командной строки
        ConfigurableApplicationContext context = SpringApplication.run(TSN_TodoApp.class, args);
        TodoClient.main(args);
        context.close();
    }
}
