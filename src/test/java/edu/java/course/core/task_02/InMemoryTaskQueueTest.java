package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryTaskQueueTest {
    final InMemoryTaskQueue queue = new InMemoryTaskQueue();

    @Test
    public void shouldAddToQueueAndPollIt() {
        final Worker worker = new Worker("John", "Doe");
        Task task = new Task(TaskPriority.HIGH, Instant.now(), worker);
        final Task add = queue.add(task);

        final Optional<Task> actualTask = queue.poll(worker);

        assertThat(actualTask).contains(task);
    }

    //TODO добавить тесты проверяющие edge cases:
    // приоритетность задач
    // если несколько задач с одинаковым приоритетом, то возвращается ранее созданая
    // если нет задач, то возвращается Optional.empty
}