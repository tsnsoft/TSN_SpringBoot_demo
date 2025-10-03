package kz.proffix4.tsn_todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Аннотация @RestController указывает, что этот класс обрабатывает HTTP-запросы 
// и возвращает данные в формате JSON
@RestController
// Аннотация @RequestMapping задаёт базовый путь для всех методов в этом контроллере (/api/tasks)
@RequestMapping("/api/tasks")
public class TaskController {

    // Аннотация @Autowired автоматически внедряет зависимость от сервиса TaskService
    @Autowired
    private TaskService taskService;

    // Метод для получения всех задач
    // Аннотация @GetMapping указывает, что этот метод обрабатывает HTTP GET-запросы 
    // по пути /api/tasks
    @GetMapping
    public List<Task> getAllTasks() {
        // Вызываем метод сервиса для получения списка задач и возвращаем его
        return taskService.getAllTasks();
    }

    // Метод для получения задачи по её ID
    // Аннотация @GetMapping("/{id}") указывает, что метод обрабатывает GET-запросы 
    // по пути /api/tasks/{id}
    // @PathVariable извлекает значение ID из URL
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        // Получаем задачу по ID из сервиса
        Task task = taskService.getTaskById(id);
        // Если задача не найдена, возвращаем HTTP-статус 404 (Not Found)
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        // Если задача найдена, возвращаем её с HTTP-статусом 200 (OK)
        return ResponseEntity.ok(task);
    }

    // Метод для создания новой задачи
    // Аннотация @PostMapping указывает, что метод обрабатывает HTTP POST-запросы по пути /api/tasks
    // @RequestBody извлекает данные задачи из тела запроса (в формате JSON)
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        // Вызываем метод сервиса для создания задачи и возвращаем созданную задачу
        return taskService.createTask(task);
    }

    // Метод для обновления существующей задачи
    // Аннотация @PutMapping("/{id}") указывает, что метод обрабатывает PUT-запросы 
    // по пути /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        // Вызываем метод сервиса для обновления задачи
        Task updatedTask = taskService.updateTask(id, task);
        // Если задача не найдена (обновление не удалось), возвращаем HTTP-статус 404
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        // Если задача обновлена, возвращаем её с HTTP-статусом 200
        return ResponseEntity.ok(updatedTask);
    }

    // Метод для удаления задачи
    // Аннотация @DeleteMapping("/{id}") указывает, что метод обрабатывает DELETE-запросы по пути /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        // Вызываем метод сервиса для удаления задачи
        // Если удаление прошло успешно, возвращаем HTTP-статус 204 (No Content)
        if (taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        }
        // Если задача не найдена, возвращаем HTTP-статус 404
        return ResponseEntity.notFound().build();
    }
}
