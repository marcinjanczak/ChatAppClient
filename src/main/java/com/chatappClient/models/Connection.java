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


public class Connection {
    private List<String> messages;
    private final MessagePanel messagePanel;
    private PrintWriter out;

    public Connection(MessagePanel messagePanel) throws IOException {
        this.messagePanel = messagePanel;
        ConnectCreator connectCreator = new ConnectCreator();
        connect(connectCreator);
    }

    public void connect(ConnectCreator connectCreator) {
        try {
            Socket socket = new Socket(connectCreator.getIpAddress(), connectCreator.getPort());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            this.out  = new PrintWriter(socket.getOutputStream(), true);


            System.out.println("Pomyślenie połączono z serwerem: " + connectCreator.getIpAddress());
            messages = new ArrayList<>();
            Thread messageReceiver = getThread(in);
            messageReceiver.start();

        }catch (IOException e ){
            SwingUtilities.invokeLater(() ->
                    messagePanel.addMessage("Błąd połączenia: " + e.getMessage()
                    ));
        }
    }

    private Thread getThread(BufferedReader in) {
        Thread messageReceiver = new Thread(() -> {
            try {
                String serverMessage;
                while (((serverMessage = in.readLine()) != null)) {
                    updateMessagePanel(serverMessage);
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() ->
                        messagePanel.addMessage("Rozłączono z serwerem" )
                );
            }

        });
        messageReceiver.setDaemon(true);
        return messageReceiver;
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

