package com.example;
import java.util.List;
import java.util.ArrayList;

public class Calculation implements Runnable {
    private final Task task;
    private final Output output;

    public Calculation(Task task, Output output) {
        this.task = task;
        this.output = output;
    }

    @Override
    public void run(){
    try{
        while (task.getRunning()) {
            Thread t = Thread.currentThread();  // get reference to the main thread
            Long task_in = task.getTask();
            System.out.println("      thread "+t.getName()+"is working on "+task_in);
            List<Long>temp=calculate(task_in);
           //Boolean isPrimaryBoolean = calculate(task_in);
            output.addResult(task_in,temp);
           //System.out.println("cos sie dzieje");
            Thread.sleep(1000);
            System.out.println("      thread "+t.getName()+"finished "+task_in);
        }
        }catch(InterruptedException e){
            System.out.println("exception detected for thread");
        }
    }
    private List<Long> calculate(Long number) {
        List<Long> temp=new ArrayList<>();
        for (long i = 1; i <= number; i++) {
            if (number % i == 0) {
                temp.add(i);
            }
        }
        return temp;
    }
}
