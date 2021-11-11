package edu.java.course.core.task_02;

import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Comparator.comparing;

public class InMemoryTaskQueue implements TaskQueue {

    final Map<Worker, PriorityQueue<Task>> workersQueue = new ConcurrentHashMap<>();

    @Override
    public Task add(Task task) {
        final Worker assignee = task.getAssignee().orElseThrow();
        final PriorityQueue<Task> tasks = workersQueue.get(assignee);
        if (tasks == null) {
            final PriorityQueue<Task> priorityQueue = new PriorityQueue<>(comparing(Task::getPriority).reversed().thenComparing(Task::getCreatedDate));
            priorityQueue.add(task);
            workersQueue.put(assignee, priorityQueue);
        } else {
            tasks.add(task);
        }
        return task;
    }

    @Override
    public Optional<Task> poll(Worker worker) {
        final PriorityQueue<Task> tasks = workersQueue.get(worker);
        return Optional.ofNullable(tasks).map(PriorityQueue::poll);
    }
}
