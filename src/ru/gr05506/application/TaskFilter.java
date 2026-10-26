package ru.gr05506.application;

import ru.gr05506.domain.Task;

public interface TaskFilter {
    boolean match(Task task);
}
