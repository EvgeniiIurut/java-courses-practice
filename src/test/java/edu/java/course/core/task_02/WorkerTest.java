package edu.java.course.core.task_02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {
    @Test
    void shouldFailIfWorkerHasNullFirstNameField() {

        var exception = assertThrows(NullPointerException.class,
                () -> new Worker(null,"lastName"));

        assertEquals(exception.getMessage(), "firstName is null");
    }
    @Test
    void shouldFailIfWorkerHasNullLastNameField() {

        var exception = assertThrows(NullPointerException.class,
                () -> new Worker("firstName",null));

        assertEquals(exception.getMessage(), "lastName is null");
    }
}