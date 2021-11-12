package edu.java.course.core.task_02;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class WorkerServiceImpl implements WorkerService {
    private Iterator<Worker> iterator;
    private List<Worker> workers;

    public WorkerServiceImpl(List<Worker> workers) {
        this.workers = Objects.requireNonNull(workers, "workers is null");
        if (workers.contains(null)) {
            throw new IllegalArgumentException("Contain null worker");
        }
        iterator = workers.iterator();
    }

    @Override
    public Worker nextWorker() {
        if (!iterator.hasNext()) {
            iterator = workers.iterator();
        }
        return Objects.requireNonNull(iterator.next(), "nextWorker is null");
    }
}
