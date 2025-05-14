package com.chatappClient;

import com.chatappClient.models.ConnectCreator;
import com.chatappClient.views.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        System.out.println();
        SwingUtilities.invokeLater(() ->{
            try{
                 new MainFrame();
            }catch (Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Bład GUI: " + e.getMessage(),
                        "Bład GUI",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
