package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

class InMemoryTaskQueueTest {


    @Test
    public void shouldAddToQueueAndPollIt() {
        final InMemoryTaskQueue queue = new InMemoryTaskQueue();
        final Worker worker = new Worker("John", "Doe");
        Task task = new Task(TaskPriority.HIGH, Instant.now(), worker);
        final Task add = queue.add(task);

        final Optional<Task> actualTask = queue.poll(worker);

        assertThat(actualTask).contains(task);
    }

    @Test
    public void shouldAddToQueueAndPollItWithPriorityOrder() {
        final InMemoryTaskQueue queue = new InMemoryTaskQueue();
        final Worker worker = new Worker("John", "Doe");
        Task task1 = new Task(TaskPriority.MEDIUM, Instant.now(), worker);
        Task task2 = new Task(TaskPriority.HIGH, Instant.now(), worker);
        Task task3 = new Task(TaskPriority.LOW, Instant.now(), worker);
        final Task add1 = queue.add(task1);
        final Task add2 = queue.add(task2);
        final Task add3 = queue.add(task3);

        final Optional<Task> actualTask = queue.poll(worker);

        assertThat(actualTask).contains(task2);
    }


    @Test
    public void shouldAddToQueueAndPollItWithPriorityOrderThanComparingInstant() {
        final InMemoryTaskQueue queue = new InMemoryTaskQueue();
        final Worker worker = new Worker("John", "Doe");

        long hourAgo = System.currentTimeMillis() - 3600;
        Instant early = Instant.ofEpochMilli(hourAgo);

        Task task1 = new Task(TaskPriority.MEDIUM, Instant.now(), worker);
        Task task2 = new Task(TaskPriority.HIGH, Instant.now(), worker);
        Task task3 = new Task(TaskPriority.HIGH, early, worker);
        Task task4 = new Task(TaskPriority.LOW, Instant.now(), worker);
        final Task add1 = queue.add(task1);
        final Task add2 = queue.add(task2);
        final Task add3 = queue.add(task3);
        final Task add4 = queue.add(task4);

        final Optional<Task> actualTask = queue.poll(worker);

        assertThat(actualTask).contains(task3);
    }

    @Test
    public void shouldReturnOptionalEmptyIfTaskQueueIsNull() {
        final InMemoryTaskQueue queue = new InMemoryTaskQueue();
        final Worker worker = new Worker("John", "Doe");

        final Optional<Task> actualTask = queue.poll(worker);

        assertFalse(actualTask.isPresent());
    }

    //TODO добавить тесты проверяющие edge cases:
    // приоритетность задач
    // если несколько задач с одинаковым приоритетом, то возвращается ранее созданая
    // если нет задач, то возвращается Optional.empty
}