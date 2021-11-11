package edu.java.course.core.task_02;


import java.util.Optional;

public interface TaskService {

    /**
     * This method assign task to the worker and add it to a queue
     *
     * Test
     * 1. Not null check
     * 2. Add to a queue
     */
    Task addTask(Task task);

    /**
     * This method returns the assigned task with the highest priority and removes it from queue .
     * In case of several task with same priority the earliest task should be returned.
     * In case of empty queue method should return Optional.empty()
     */
    Optional<Task> nextTask(Worker assignee);
}