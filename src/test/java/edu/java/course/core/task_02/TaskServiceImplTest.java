package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static java.time.Instant.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    final WorkerService workerService = mock(WorkerService.class);
    final TaskQueue taskQueue = mock(TaskQueue.class);
    final TaskServiceImpl taskService = new TaskServiceImpl(workerService, taskQueue);

    @Test
    void shouldFailIfWorkerServiceIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new TaskServiceImpl(null, taskQueue));

        assertEquals(exception.getMessage(), "WorkerService is null");
    }

    @Test
    void shouldAssignWorkerAndAddTaskToQueue() {
        final Instant createdDate = now();
        final Task task = new Task(TaskPriority.LOW, createdDate);
        final Worker worker = new Worker("John", "Doe");
        when(workerService.nextWorker()).thenReturn(worker);
        when(taskQueue.add(any())).thenAnswer(invocation -> invocation.getArguments()[0]);

        final Task updatedTask = taskService.addTask(task);

        assertThat(updatedTask.getAssignee()).contains(worker);
        verify(taskQueue).add(updatedTask);
    }

    @Test
    void shouldReturnTaskFromQueue() {
        final Worker worker = new Worker("John", "Doe");

        taskService.nextTask(worker);

        verify(taskQueue).poll(worker);
    }
}
