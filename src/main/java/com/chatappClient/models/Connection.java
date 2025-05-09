package com.chatappClient.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Stack;

public class Connection {
    private Stack<String> messagesStack;
    public Connection(){
        ConnectCreator connectCreator = new ConnectCreator();
        connect(connectCreator);
    }

    public void connect(ConnectCreator connectCreator){
        try (
                Socket socket = new Socket(connectCreator.getIpAddress(), connectCreator.getPort());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Pomyślenie połączono z serwerem: " + connectCreator.getIpAddress());
            messagesStack = new Stack<>();

            Thread messageReceiver = new Thread(() -> {
                try {
                    String serverMessage;
                    while (((serverMessage = in.readLine()) != null)) {
                        System.out.println(serverMessage);
                        messagesStack.add(serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Rozłączono z serwerem.");
                }

            });
            messageReceiver.start();
            String userInput;
            while ((userInput = stdIn.readLine()) != null){
                out.println(userInput);
            }
        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem. "+ e.getMessage());
        }

    }
    public Stack<String> getMessagesStack(){
        return messagesStack;
    }
}

