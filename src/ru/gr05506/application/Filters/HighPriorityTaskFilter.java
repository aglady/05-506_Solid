package ru.gr05506.application.Filters;

import ru.gr05506.application.TaskFilter;
import ru.gr05506.domain.Task;

public class HighPriorityTaskFilter implements TaskFilter {
    @Override
    public boolean match(Task task) {
        return task.getPriority().equals("HIGH");
    }
}
