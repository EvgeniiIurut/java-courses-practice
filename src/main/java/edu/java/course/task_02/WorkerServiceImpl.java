package edu.java.course.core.task_02;

import java.util.Objects;
import java.util.Stack;

public class WorkerServiceImpl implements WorkerService {
    private Stack<Worker> workers;

    public WorkerServiceImpl(Stack<Worker> workers) {
        this.workers = Objects.requireNonNull(workers, "workers is null");
    }

    @Override
    public Worker nextWorker() {
        return workers.pop();
    }
}
