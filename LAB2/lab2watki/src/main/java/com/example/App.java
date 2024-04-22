package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Task task = new Task();
        Output output=new Output();
        List<Thread> threads = new ArrayList<>();
        int numThreads = Integer.parseInt(args[0]); 
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new Calculation(task,output));
            thread.start();
            threads.add(thread);
        }

        try {
        Scanner text_Scanner=new Scanner(new File("test2.txt"));
        while(text_Scanner.hasNextLong()){
            task.addTask(text_Scanner.nextLong());
        }
        text_Scanner.close();
        }catch(FileNotFoundException e){
            System.out.println("Plik nie znaleziony");
        }
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wpisz numer zadania lub 'exit' aby zakończyć:");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                task.stop();
                try {
                    if (task.getTask()==null){
                        for(Thread thread : threads){
                            thread.interrupt();
                    }
                    break;
            }
                } catch (InterruptedException e) {
                    System.out.println("watek przestal dzialac");
                    //e.printStackTrace();
                }
            }
            try {
                int task_in = Integer.parseInt(input);
                task.addTask(task_in);
            } catch (NumberFormatException e) {
                System.out.println("Błędny format numeru zadania.");
            }
        }
        //output.toString();
        scanner.close();
        //Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        System.out.println("End");
        System.out.println("Reults");
        List<Number> results = output.getResults();
        for (Number number : results) {
            System.out.println(number);
        }
        try{ PrintWriter zapis = new PrintWriter("zapis.txt");
        for (Number number : results) {
            zapis.println(number);
        }
        zapis.close();
        }catch(FileNotFoundException e){
            System.out.println("Error writing to file");
        }
       
       
    }
}
