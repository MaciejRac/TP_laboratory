package com.example;

import java.io.*;
//import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private ObjectOutputStream sockOut;
    private ObjectInputStream sockIn;

    public Client() {
        try {
            String serverHost = "127.0.0.1";
            int serverPort = 50000;
            this.socket = new Socket(serverHost, serverPort);
            this.sockOut = new ObjectOutputStream(socket.getOutputStream());
            this.sockIn = new ObjectInputStream(socket.getInputStream());
            try{
            System.out.println(sockIn.readObject());
            }catch(ClassNotFoundException exp){
                System.err.println(exp);
            }
            
            Thread receiveThread = new Thread(this::receiveMessages);
            receiveThread.start();

            Thread sendThread = new Thread(this::sendMessages);
            sendThread.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createSocket(){
        try {
            String serverHost = "127.0.0.1";
            int serverPort = 50000;
            Socket socket = new Socket(serverHost, serverPort);

            ObjectOutputStream sockOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream sockIn = new ObjectInputStream(socket.getInputStream());
            System.out.println(sockIn.readObject());

            Scanner scanner=new Scanner(System.in);
            int n =0;
            //System.out.println(sockIn.readObject());
            Message msg = new Message();
            System.out.println("halo");
            while (true) {
                String content = scanner.nextLine();
                if (content.equals("end")) {
                    msg.setContent(content);
                    sockOut.writeObject(msg);
                    break;
                } else {
                    n++;
                    msg.setNumber(n);
                    msg.setContent(content);
                    sockOut.writeObject(msg);
                }
            }
            scanner.close();
            System.out.println(sockIn.readObject());
            sockOut.flush();
            sockOut.close();
            sockIn.close();
            socket.close();

        } catch (UnknownHostException exc) {
            System.out.println("nieznany host");
        } catch (SocketException exc) {
            System.out.println("Blad Socket Exception");
            System.err.println(exc);
        } catch (IOException exc) {
            System.err.println(exc);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveMessages() {
        try {
            while (true) {
                Object received = sockIn.readObject();
                if (received instanceof Message) {
                    System.out.println(received.toString());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void sendMessages() {
        try {
            Scanner scanner = new Scanner(System.in);
            int i=0;
            while (true) {
                System.out.print("Type a message (type 'end' to finish): ");
                String content = scanner.nextLine();
                if (content.equals("end")) {
                    Message msg = new Message(i,content);
                    sockOut.writeObject(msg);
                    break;
                }
                Message msg = new Message(i,content);
                sockOut.writeObject(msg);
                i++;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Client start!");
        new Client();
        //Client c = new Client();
        //c.createSocket();
        //System.out.println("Client end!");
    }
}