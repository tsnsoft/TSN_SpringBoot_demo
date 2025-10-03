package kz.proffix4.tsn_todoapp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

// Аннотация @Service указывает, что этот класс является сервисом Spring, 
// который содержит бизнес-логику
@Service
public class TaskService {

    // Список для хранения всех задач (в памяти, без базы данных)
    private final List<Task> tasks = new ArrayList<>();
    // Счётчик для генерации уникальных ID для задач
    private final AtomicLong counter = new AtomicLong();

    // Метод возвращает все задачи из списка
    public List<Task> getAllTasks() {
        return tasks; // Просто возвращаем список задач
    }

    // Метод ищет задачу по её ID
    public Task getTaskById(Long id) {
        // Перебираем все задачи в списке
        for (Task task : tasks) {
            // Если ID задачи совпадает с переданным, возвращаем задачу
            if (task.getId().equals(id)) {
                return task;
            }
        }
        // Если задача не найдена, возвращаем null
        return null;
    }

    // Метод создаёт новую задачу
    public Task createTask(Task task) {
        // Генерируем новый уникальный ID и устанавливаем его для задачи
        task.setId(counter.incrementAndGet());
        // Добавляем задачу в список
        tasks.add(task);
        // Возвращаем созданную задачу
        return task;
    }

    // Метод обновляет существующую задачу по ID
    public Task updateTask(Long id, Task updatedTask) {
        // Ищем задачу по ID
        Task task = getTaskById(id);
        // Если задача найдена, обновляем её поля
        if (task != null) {
            task.setDescription(updatedTask.getDescription());
            task.setCompleted(updatedTask.isCompleted());
        }
        // Возвращаем обновлённую задачу или null, если задача не найдена
        return task;
    }

    // Метод удаляет задачу по ID
    public boolean deleteTask(Long id) {
        // Перебираем список задач и удаляем задачу с указанным ID
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                tasks.remove(i);
                return true; // Возвращаем true, если задача успешно удалена
            }
        }
        return false; // Возвращаем false, если задача не найдена
    }
}
