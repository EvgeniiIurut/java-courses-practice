package edu.java.course.core.task_02;

import java.util.ArrayList;

public interface WorkerService {
    ArrayList<Worker> workers = new ArrayList<>();


    Worker nextWorker();
}
