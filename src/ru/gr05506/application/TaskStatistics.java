package ru.gr05506.application;

public record TaskStatistics(
        int total,
        int done,
        int overdue
) {
}
