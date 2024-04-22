package com.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket connection;
    private Socket connection1;
    private Socket connection2;

    public ClientHandler(Socket connection){
        this.connection=connection;
    }
    
    public ClientHandler(Socket connection1,Socket connection2){
        this.connection1=connection1;
        this.connection2=connection2;
    }

    public void run(){
        if(connection!=null){
            try{
            System.out.println("Handlin client "+connection);
            ObjectOutputStream sockOut = new ObjectOutputStream(connection.getOutputStream());
            sockOut.writeObject("ready");
            ObjectInputStream sockIn = new ObjectInputStream(connection.getInputStream());
            int n=(int)sockIn.readObject();
            sockOut.writeObject("ready for messages");
            Message msg = new Message();
            for(int i=0;i<n;i++){
                msg = (Message) sockIn.readObject();
                System.out.println(msg);
            }
            sockOut.writeObject("finished");
            sockOut.close();
            sockIn.close();
            connection.close();
            }catch(IOException e){
                throw new RuntimeException(e);
            } catch (ClassNotFoundException o) {
                throw new RuntimeException(o);
            }
        }
        else if(connection1!=null && connection2!=null){
            System.out.println("Handling clients: " + connection1 + " and " + connection2);
        
            Thread client1Thread = new Thread(() -> {
                try {
                    ObjectOutputStream out2 = new ObjectOutputStream(connection2.getOutputStream());
                    ObjectInputStream in1 = new ObjectInputStream(connection1.getInputStream());
                    out2.writeObject("ready");
                    while (true) {
                        Message msg = (Message) in1.readObject();
                        out2.writeObject(msg);
                        if (msg.getContent().equals("end")) break;
                    }
                    out2.writeObject("Connection closed");
                    out2.flush();
                    out2.close();
                    in1.close();
                    connection1.close();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        
            Thread client2Thread = new Thread(() -> {
                try {
                    ObjectOutputStream out1 = new ObjectOutputStream(connection1.getOutputStream());
                    ObjectInputStream in2 = new ObjectInputStream(connection2.getInputStream());
                    out1.writeObject("ready");
    
        
                    while (true) {
                        Message msg = (Message) in2.readObject();
                        out1.writeObject(msg);
                        if (msg.getContent().equals("end")) break;
                    }
                    out1.writeObject("Connection closed");
                    out1.flush();
                    out1.close();
                    in2.close();
                    connection2.close();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        
            client1Thread.start();
            client2Thread.start();
        
            try {
                client1Thread.join();
                client2Thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
