package edu.java.course.core.task_02;

import java.util.Optional;

public interface TaskQueue {

    Task add(Task task);

    Optional<Task> poll(Worker worker);
}
