package edu.java.course.core.task_02;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WorkerServiceImpl implements WorkerService {
    private Iterator<Worker> iterator;
    private Set<Worker> workers;

    public WorkerServiceImpl(Set<Worker> workers) {
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
