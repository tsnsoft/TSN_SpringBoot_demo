package kz.proffix4.tsn_todoapp_client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.json.JSONArray; // Для работы с JSON-массивами
import org.json.JSONObject; // Для работы с JSON-объектами

// Простое консольное приложение для вызова REST API TSN_TodoApp
public class TodoClient {

    // Базовый URL API
    private static final String API_URL = "http://localhost:8080/api/tasks";
    // HTTP-клиент для отправки запросов
    private static final HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Меню для выбора действия
            System.out.println("\nВыберите действие:");
            System.out.println("1. Показать все задачи");
            System.out.println("2. Создать задачу");
            System.out.println("3. Обновить задачу");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Выход");
            System.out.print("Ваш выбор: ");

            String choice = scanner.nextLine();
            try {
                switch (choice) {
                    case "1":
                        getAllTasks();
                        break;
                    case "2":
                        createTask(scanner);
                        break;
                    case "3":
                        updateTask(scanner);
                        break;
                    case "4":
                        deleteTask(scanner);
                        break;
                    case "5":
                        System.out.println("Выход...");
                        return;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    // Получение всех задач (GET /api/tasks)
    private static void getAllTasks() throws Exception {
        // Создаём HTTP-запрос GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .GET()
                .build();
        // Отправляем запрос и получаем ответ как строку
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверяем статус ответа
        if (response.statusCode() == 200) {
            // Парсим JSON-ответ в JSONArray
            JSONArray tasks = new JSONArray(response.body());
            // Если список пуст, выводим сообщение
            if (tasks.isEmpty()) {
                System.out.println("Список задач пуст.");
                return;
            }
            // Выводим задачи в красивом формате
            System.out.println("Список задач:");
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject task = tasks.getJSONObject(i);
                Long id = task.getLong("id");
                String description = task.getString("description");
                boolean completed = task.getBoolean("completed");
                System.out.printf("%d. ID: %d, Описание: %s, Выполнена: %s%n",
                        i + 1, id, description, completed ? "да" : "нет");
            }
        } else {
            System.out.println("Ошибка: HTTP " + response.statusCode());
        }
    }

    // Создание новой задачи (POST /api/tasks)
    private static void createTask(Scanner scanner) throws Exception {
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        String json = "{\"description\":\"" + description + "\",\"completed\":false}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Создана задача: " + response.body());
        } else {
            System.out.println("Ошибка: HTTP " + response.statusCode());
        }
    }

    // Обновление задачи (PUT /api/tasks/{id})
    private static void updateTask(Scanner scanner) throws Exception {
        System.out.print("Введите ID задачи: ");
        String id = scanner.nextLine();
        System.out.print("Введите новое описание: ");
        String description = scanner.nextLine();
        System.out.print("Задача выполнена? (true/false): ");
        boolean completed = Boolean.parseBoolean(scanner.nextLine());

        String json = "{\"description\":\"" + description + "\",\"completed\":" + completed + "}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            System.out.println("Обновлена задача: " + response.body());
        } else {
            System.out.println("Ошибка: HTTP " + response.statusCode());
        }
    }

    // Удаление задачи (DELETE /api/tasks/{id})
    private static void deleteTask(Scanner scanner) throws Exception {
        System.out.print("Введите ID задачи: ");
        String id = scanner.nextLine();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/" + id))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 204) {
            System.out.println("Задача удалена");
        } else {
            System.out.println("Ошибка: HTTP " + response.statusCode());
        }
    }
}
