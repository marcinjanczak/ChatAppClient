package com.chatappClient;

import com.chatappClient.views.MainFrame;

import javax.swing.*;

public class ChatClient {
    public static void main(String[] args) {
        System.out.println();
        SwingUtilities.invokeLater(() ->{
            try{
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
                System.out.println("Powinnno być widać.");
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
