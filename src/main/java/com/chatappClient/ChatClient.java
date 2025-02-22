package com.chatappClient;

import com.chatappClient.models.ConnectCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args){


        ConnectCreator connectCreator = new ConnectCreator();

        try (Socket socket = new Socket(connectCreator.getIpAddress(), connectCreator.getPort());
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Pomyślenie połączono z serwerem: " + connectCreator.getIpAddress());

            Thread messageReceiver = new Thread(() -> {
                    try {
                        String serverMessage;
                        while (((serverMessage = in.readLine()) != null)) {
                            System.out.println(serverMessage);
                        }
                    } catch (IOException e) {
//                        System.out.println("Rozłączono z serwerem");
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
}
