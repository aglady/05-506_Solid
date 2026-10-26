package ru.gr05506.application;

import ru.gr05506.application.Filters.ActiveTaskFilter;
import ru.gr05506.application.Filters.AllTasksFilter;
import ru.gr05506.application.Filters.HighPriorityTaskFilter;
import ru.gr05506.application.Filters.OverdueTasksFilter;

public enum FilterType {
    ALL("Все", new AllTasksFilter()),
    ACTIVE("Активные",new ActiveTaskFilter()),
    OVERDUE("Просроченные", new OverdueTasksFilter()),
    HIGH_PRIORITY("Высокий приоретет", new HighPriorityTaskFilter());

    private final String displayName;
    private final TaskFilter filter;

    FilterType(String displayName, TaskFilter filter) {
        this.displayName = displayName;
        this.filter = filter;
    }

    public String getDisplayName() {
        return displayName;
    }

    public TaskFilter getFilter() {
        return filter;
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
