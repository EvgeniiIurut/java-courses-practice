package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkerServiceImplTest {
    final List<Worker> workers = Arrays.asList(new Worker("John", "Doe"), new Worker("John2", "Doe2"));
    final WorkerService workerService = new WorkerServiceImpl(workers);


    @Test
    void shouldReturnNextWorker() {
        assertEquals(new Worker("John", "Doe"), workerService.nextWorker());
    }

    @Test
    void shouldReturnFirstWorker() {
        workerService.nextWorker();
        workerService.nextWorker();
        assertEquals(new Worker("John", "Doe"), workerService.nextWorker());
    }


    @Test
    void shouldFailIfWorkersIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new WorkerServiceImpl(null));

        assertEquals(exception.getMessage(), "workers is null");
    }


    @Test
    void shouldFailIfWorkersContainNull() {
        final List<Worker> workers = Arrays.asList(null, null);
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new WorkerServiceImpl(workers));

        assertEquals(exception.getMessage(), "Contain null worker");
    }

}
