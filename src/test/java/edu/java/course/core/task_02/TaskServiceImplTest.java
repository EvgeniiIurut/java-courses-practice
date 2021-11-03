package edu.java.course.core.task_02;

import edu.java.course.core.task_01.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.*;
import static java.time.Instant.now;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskServiceImplTest {

    @Test
    void shouldFailIfWorkerServiceIsNull() {

        var exception = assertThrows(NullPointerException.class,
                () -> new TaskServiceImpl(null));

        assertEquals(exception.getMessage(), "WorkerService is null");
    }


}
