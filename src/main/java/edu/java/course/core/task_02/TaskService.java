package edu.java.course.core.task_02;


public interface TaskService {

    /**
     * This method assign task to the worker and add it to a queue
     *
     * @return
     */
    Task addTask(Task task);

    /**
     * This method returns the task with the highest priority and removes it from queue.
     * In case of several task with same priority the earliest task should be returned.
     */
    Task nextTask(Worker assignee);
}
