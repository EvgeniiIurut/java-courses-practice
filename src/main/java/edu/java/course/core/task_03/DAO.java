package edu.java.course.core.task_03;

import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {

    void add(T t);

    Optional<T> get(UUID id);

    void getAll();

}

