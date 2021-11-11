package edu.java.course.core.task_02;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

public class Task {

    private final TaskPriority priority;
    private final Instant createdDate;
    private final Optional<Worker> assignee;

    public Task(TaskPriority priority, Instant createdDate) {
        this.priority = Objects.requireNonNull(priority, "priority is null");
        this.createdDate = Objects.requireNonNull(createdDate, "createdDate is null");
        this.assignee = Optional.empty();
    }

    public Task(TaskPriority priority, Instant createdDate, Worker worker) {
        this.priority = Objects.requireNonNull(priority, "priority is null");
        this.createdDate = Objects.requireNonNull(createdDate, "createdDate is null");
        this.assignee = Optional.of(worker);
    }

    public Task assign(Worker worker) {
        return new Task(this.priority, this.createdDate, worker);
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public Optional<Worker> getAssignee() {
        return assignee;
    }
}
