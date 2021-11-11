package edu.java.course.core.task_02;

import java.util.Objects;

public class Worker {
    private final String firstName;
    private final String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker)) return false;
        Worker worker = (Worker) o;
        return Objects.equals(firstName, worker.firstName) && Objects.equals(lastName, worker.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    public Worker(String firstName, String lastName) {
        this.firstName = Objects.requireNonNull(firstName, "firstName is null");
        this.lastName = Objects.requireNonNull(lastName, "lastName is null");
    }
}
