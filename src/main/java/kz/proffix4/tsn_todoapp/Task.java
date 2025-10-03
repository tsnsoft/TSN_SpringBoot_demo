package kz.proffix4.tsn_todoapp;

// Класс Task представляет модель задачи в приложении
public class Task {

    // Уникальный идентификатор задачи
    private Long id;
    // Описание задачи (например, "Сходить в магазин")
    private String description;
    // Статус выполнения задачи (true — выполнена, false — не выполнена)
    private boolean completed;

    // Конструктор без параметров для создания пустой задачи
    public Task() {
    }

    // Конструктор с параметрами для удобного создания задачи
    public Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    // Геттер для получения ID задачи
    public Long getId() {
        return id;
    }

    // Сеттер для установки ID задачи
    public void setId(Long id) {
        this.id = id;
    }

    // Геттер для получения описания задачи
    public String getDescription() {
        return description;
    }

    // Сеттер для установки описания задачи
    public void setDescription(String description) {
        this.description = description;
    }

    // Геттер для получения статуса выполнения задачи
    public boolean isCompleted() {
        return completed;
    }

    // Сеттер для установки статуса выполнения задачи
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
