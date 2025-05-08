package com.chatappClient;

import com.chatappClient.models.ConnectCreator;
import com.chatappClient.models.Connection;
import com.chatappClient.views.MainFrame;

import javax.swing.*;

public class ChatClient {
    public static void main(String[] args) {

//        ConnectCreator connectCreator = new ConnectCreator();
//        Connection connection = new Connection();
//        connection.connect(connectCreator);

        SwingUtilities.invokeLater(MainFrame::new);
    }
}
