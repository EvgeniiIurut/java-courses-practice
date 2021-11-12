package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskTest {
    @Test
    void shouldFailIfPriorityIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new Task(null, Instant.EPOCH));

        assertEquals(exception.getMessage(), "priority is null");
    }

    @Test
    void shouldFailIfCreatedDateIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new Task(TaskPriority.LOW, null));

        assertEquals(exception.getMessage(), "createdDate is null");
    }
}
