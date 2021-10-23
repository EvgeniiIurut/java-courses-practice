package edu.java.course.core.task_02;

import java.time.Instant;
import java.util.Optional;

public class Task {

    private final TaskPriority priority;
    private final Instant createdDate;
    private final Optional<Worker> assignee;

    public Task(TaskPriority priority, Instant createdDate) {
        this.priority = priority;
        this.createdDate = createdDate;
        this.assignee = Optional.empty();
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public Optional<Worker> getAssignee() {
        return assignee;
    }

}
