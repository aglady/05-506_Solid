package ru.gr05506.application.Filters;

import ru.gr05506.application.TaskFilter;
import ru.gr05506.domain.Task;

import java.time.LocalDate;

public class OverdueTasksFilter implements TaskFilter {
    @Override
    public boolean match(Task task) {
        return !task.isDone() && task.getDeadline().isBefore(LocalDate.now());
    }
}
