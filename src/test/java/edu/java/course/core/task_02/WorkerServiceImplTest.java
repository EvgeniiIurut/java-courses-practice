package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerServiceImplTest {
    final Set<Worker> workers = new HashSet<>(Arrays.asList(new Worker("John", "Doe"), new Worker("John2", "Doe2")));
    final WorkerService workerService = new WorkerServiceImpl(workers);


    @Test
    void shouldReturnNextWorker() {
        assertThat(workerService.nextWorker() != null);
    }

    @Test
    public void shouldIterateOverWorkers(){
        workers.forEach(worker -> {
            assertEquals(worker, workerService.nextWorker());
        });
    }

    @Test
    void shouldFailIfWorkersIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new WorkerServiceImpl(null));

        assertEquals(exception.getMessage(), "workers is null");
    }

    @Test
    void shouldFailIfWorkersContainNull() {
        final Set<Worker> workers = new HashSet<>(Arrays.asList(null, null));
        var exception = assertThrows(IllegalArgumentException.class,
                () -> new WorkerServiceImpl(workers));

        assertEquals(exception.getMessage(), "Contain null worker");
    }
}
