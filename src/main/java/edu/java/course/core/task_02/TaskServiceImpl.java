package edu.java.course.core.task_02;

import java.util.Objects;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {
    private final WorkerService workerService;
    private final TaskQueue tasks;

    public TaskServiceImpl(WorkerService workerService, TaskQueue queue) {
        this.workerService = Objects.requireNonNull(workerService, "WorkerService is null");
        this.tasks = Objects.requireNonNull(queue, "queue is null");
    }

    private int getEnumPriority(Task task) {
        switch (task.getPriority()) {
            case LOW:
                return 0;
            case MEDIUM:
                return 1;
            case HIGH:
                return 2;
            default:
                return Integer.MAX_VALUE;
        }
    }

    @Override
    public Task addTask(Task task) {
        Worker worker = workerService.nextWorker();
        Task assignedTask = task.assign(worker);
        return tasks.add(assignedTask);
    }

    @Override
    public Optional<Task> nextTask(Worker assignee) {
        return tasks.poll(assignee);
    }
}
