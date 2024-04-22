package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket=null;
    private boolean serverIsRunning;

    public Server(int port, String host ){
        try{
            InetSocketAddress isa = new InetSocketAddress(host,port);
            serverSocket=new ServerSocket();
            serverSocket.bind(isa);
            serverIsRunning=true;
        }catch(IOException e){
            System.out.println(e);
        }
    }

    public void start(){
        while(serverIsRunning){
            try{
                Socket connection = null;
                connection = serverSocket.accept();
                System.out.println("new Client connected "+connection);
                Socket connection2 = null;
                connection2 = serverSocket.accept();
                System.out.println("new Client connected "+connection2);
                Thread clientThread =new Thread (new ClientHandler(connection,connection2));
                clientThread.start();
            }catch(IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void stop(){
        serverIsRunning=false;
        try{
            serverSocket.close();
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.println("server closed");
    }

    public static void main(String[] args) {
        System.out.println("Server start!");
        String host = "127.0.0.1";
        int port =50000;
        Server server = new Server(port,host);
        //server.createSocket();
        
        Thread consoleThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("end")) {
                    server.stop();
                    break;
                }
            }
            scanner.close();
        });
        consoleThread.start();
        server.start();
        
        System.out.println("Server end!");
    }
}