package com.chatappClient;

import com.chatappClient.views.MainFrame;

import javax.swing.*;

public class ChatClient {
    public static void main(String[] args){
        System.out.println();
        SwingUtilities.invokeLater(() ->{
            try{
                 new MainFrame();
            }catch (Exception e){
                System.err.println("Błąd inicjalizacji GUI "+ e.getMessage());
                JOptionPane.showMessageDialog(null,
                        "Bład GUI: " + e.getMessage(),
                        "Bład GUI",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
