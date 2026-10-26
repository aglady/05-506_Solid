package ru.gr05506.application;

import java.time.LocalDate;
import ru.gr05506.domain.Task;

public enum FilterType {
    ALL("Все"){
        boolean match(Task task){ return true;}
    },
    ACTIVE("Активные"){
        boolean match(Task task) {
            return (!task.isDone());
        }
    },
    OVERDUE("Просроченные"){
        boolean match(Task task) {
            return !task.isDone() && task.getDeadline().isBefore(LocalDate.now());
        }
    },
    HIGH_PRIORITY("Высокий приоритет"){
        boolean match(Task task) {
            return task.getPriority().equals("HIGH");
        }
    };

    abstract boolean match(Task task);

    private final String displayName;


    FilterType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


    public static FilterType fromDisplayName(String name) {
        for (FilterType type: values()) {
            if (type.displayName.equals(name)){
                return type;
            }
        }
        return ALL;
    }
}
