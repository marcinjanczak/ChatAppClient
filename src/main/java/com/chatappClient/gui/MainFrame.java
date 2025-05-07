package com.chatappClient.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private int DEFAULT_WIDTH = 800;
    private int DEFAULT_HEIGHT = 600;
    public MainFrame(){
        super("Aplikacja chatowa");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);
        setVisible(true);

    }
}
