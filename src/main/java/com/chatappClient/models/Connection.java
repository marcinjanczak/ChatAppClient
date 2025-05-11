package com.chatappClient.models;

import com.chatappClient.views.panels.MessagePanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Connection {
    private List<String> messages;
    private MessagePanel messagePanel;

    public Connection(MessagePanel messagePanel){
        this.messagePanel = messagePanel;
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
            messages = new ArrayList<>();

            Thread messageReceiver = new Thread(() -> {
                try {
                    String serverMessage;
                    while (((serverMessage = in.readLine()) != null)) {
                        final String msg = serverMessage;
                        updateMessagePanel(msg);
                    }
                } catch (IOException e) {
                    SwingUtilities.invokeLater(() ->
                            messagePanel.addMessage("Rozłączono z serwerem" )
                    );
                }

            });
            messageReceiver.setDaemon(true);
            messageReceiver.start();
        } catch (IOException e) {
            System.err.println("Błąd połączenia z serwerem. "+ e.getMessage());
        }

    }
    public void sendMessage(String message){
        if(out != null){
            out.println(message);
        }else {
            SwingUtilities.invokeLater(() ->
                    messagePanel.addMessage("Nie można wysłać - brak połączenia")
            );
        }

    }
    private void updateMessagePanel(String message){
        messages.add(message);

        SwingUtilities.invokeLater(() ->{
            messagePanel.addMessage(message);
        });
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }
}

