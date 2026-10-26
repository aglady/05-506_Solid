package ru.gr05506.ui;

import ru.gr05506.application.FilterType;
import ru.gr05506.application.TaskService;
import ru.gr05506.application.TaskStatistics;
import ru.gr05506.domain.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

// GOD OBJECT: форма, разбор дат, фильтры, выполнение задач и статистика в одном классе
public class TaskPresenter {
    private final TaskView view;
    // SINGLETON: зависимость скрыта внутри класса, в конструкторе её не видно
    private final TaskService service = TaskService.getInstance();

    public TaskPresenter(TaskView view) {
        this.view = view;
    }

    public void start() {
        refresh("Все");
    }

    public void saveClicked() {
        try {
            LocalDate deadline = parseDeadline(view.getDeadlineText());
            service.createTask(view.getTaskTitle(), deadline, view.getPriority(), view.isArchived());
            view.clearForm();
            refresh(view.getFilter());
        } catch (IllegalArgumentException ex) {
            view.showError(ex.getMessage());
        }
    }

    public void completeSelected() {
        Task task = view.getSelectedTask();
        if (task == null) {
            view.showError("Выберите задачу в списке");
            return;
        }
        task.setDone(true);   // ANEMIC: правило выполнения живёт снаружи задачи
        refresh(view.getFilter());
    }

    public void filterChanged() {
        refresh(view.getFilter());
    }

    // O-1: новый фильтр требует новой ветки if
    private void refresh(String filterName) {
        view.showTasks(service.filterdedTasks(FilterType.fromDisplayName(filterName)));
        view.showStatistics(formatStatistics(service.getStatistics()));
    }

    private String formatStatistics(TaskStatistics s){
        return "Всего: " + s.total() + ", выполнено: " + s.done() + ", просрочено: " + s.overdue();
    }

    private LocalDate parseDeadline(String text) {
        if (text == null || text.isBlank())
            throw new IllegalArgumentException("Дата выполнения не может быть пустой");
        try {
            return LocalDate.parse(text.trim());
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Дата должна быть в формате yyyy-MM-dd");
        }
    }
}