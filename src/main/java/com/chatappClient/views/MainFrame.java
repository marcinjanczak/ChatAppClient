package com.chatappClient.views;

import com.chatappClient.models.Connection;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MainFrame extends JFrame {
//    Connection connection;
    Stack<String> messageStack;

    private final MenuBar menuBar;

    private final JPanel messagePanel;
    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 600;
//    private final
    public MainFrame(){
        super("Aplikacja chatowa");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        messagePanel = messagesPanel();
        add(messagePanel,BorderLayout.CENTER);

//        connection = new Connection();

        menuBar = new MenuBar();


        setJMenuBar(menuBar);
        setMenuBarListeners();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private JPanel messagesPanel(){
        var panel = new JPanel(new BorderLayout());
        JTextArea messageTextArea;
//        messageStack = connection.getMessagesStack();
        messageStack = new Stack<>();
        messageStack.add("JD");
        messageStack.add("JD1");
        messageStack.add("JD2");
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(true);
        add(new JScrollPane(messageTextArea),BorderLayout.CENTER);

        updateMessageTextArea();

        return panel;
    }
    public void updateMessageTextArea(){
        StringBuilder sb = new StringBuilder();
        for (int i = messageStack.size() - 1; i >= 0; i--) {
            sb.append(messageStack.get(i));
        }
    }
    private JPanel buttonPanel(){
        var panel = new JPanel();
        return panel;
    }


    private void setMenuBarListeners(){
        menuBar.getEndMenuItem().addActionListener(e -> System.exit(0));

    }
}
