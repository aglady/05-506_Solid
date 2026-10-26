package ru.gr05506.application.Filters;

import ru.gr05506.application.TaskFilter;
import ru.gr05506.domain.Task;

public class ActiveTaskFilter implements TaskFilter {
    @Override
    public boolean match(Task task) {
        return (!task.isDone());
    }
}
