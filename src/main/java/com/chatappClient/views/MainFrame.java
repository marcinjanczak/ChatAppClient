package com.chatappClient.views;

import com.chatappClient.models.Connection;
import com.chatappClient.views.panels.MessagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MainFrame extends JFrame {
    Connection connection;
    Stack<String> messageStack;
    JPanel buttonPanel;
    JPanel messagePanel;

    private final MenuBar menuBar;

//    private final JPanel messagePanel;
    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 600;
//    private final
    public MainFrame(){
        super("Aplikacja chatowa");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new MenuBar();
        messagePanel = new MessagePanel();



        buttonPanel = getButtonPanel();
        add(messagePanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);



        setJMenuBar(menuBar);
        setMenuBarListeners();
        setLocationRelativeTo(null);
        setVisible(true);

//        connection = new Connection();

    }
    private JPanel getButtonPanel(){
        var panel = new JPanel();
        JButton sendButon = new JButton("Wyslij");
        panel.add(sendButon);
        return panel;
    }


    private void setMenuBarListeners(){
        menuBar.getEndMenuItem().addActionListener(e -> System.exit(0));

    }
}
