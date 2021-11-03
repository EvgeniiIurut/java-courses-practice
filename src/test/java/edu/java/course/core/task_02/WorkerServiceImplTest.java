package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkerServiceImplTest {

    @Test
    void shouldFailIfWorkersIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new WorkerServiceImpl(null));

        assertEquals(exception.getMessage(), "workers is null");
    }
}
