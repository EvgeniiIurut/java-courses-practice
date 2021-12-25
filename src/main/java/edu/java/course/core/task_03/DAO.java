package edu.java.course.core.task_03;

import java.util.UUID;

public interface DAO<T> {

    void add(T t);

    void get(UUID id);

    void getAll();

}

