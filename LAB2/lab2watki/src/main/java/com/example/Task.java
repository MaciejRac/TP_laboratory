package com.example;
import java.util.LinkedList;
import java.util.Queue;

public class Task {
    private final Queue<Long> tasks = new LinkedList<>();
    private boolean isRunning = true;

    public synchronized void addTask(long task) {
        tasks.offer(task);
        notify();
    }

    public void stop(){
        isRunning=false;
    }
    public boolean getRunning(){
        return isRunning;
    }
    public synchronized Long getTask() throws InterruptedException {
            while (tasks.isEmpty()) {
                wait();
            }
            return tasks.poll();
    }
    
}
