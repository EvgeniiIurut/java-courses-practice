package edu.java.course.core.task_02;

import java.time.Instant;
import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class TaskServiceImpl implements TaskService {
    private WorkerService workerService;
    public PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparing(Task::getPriority).reversed().thenComparing(Task::getCreatedDate));

    public TaskServiceImpl(WorkerService workerService) {
        this.workerService = Objects.requireNonNull(workerService, "WorkerService is null");
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
        TaskPriority priority = task.getPriority();
        Instant instant = task.getCreatedDate();
        Worker worker = workerService.nextWorker();
        Task task_temp = new Task(priority, instant, worker);
        tasks.add(task_temp);
        return task_temp;
    }

    @Override
    public Task nextTask(Worker assignee) {
        Task task = tasks.isEmpty() ? null : tasks.poll();
        return task;
    }
}
