package com.chatappClient.views;

import com.chatappClient.models.Connection;
import com.chatappClient.views.panels.MessagePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private Connection connection;
    private final MenuBar menuBar;
    private final JPanel buttonPanel;
    private final MessagePanel messagePanel;

    private JButton sendButton;
//    private inputField = new JTextField(20);

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 600;
    public MainFrame() throws IOException {
        super("Aplikacja chatowa");
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new MenuBar();
        messagePanel = new MessagePanel();

        messagePanel.addMessage("Testowa Wiadomość");
        buttonPanel = getButtonPanel();
        add(messagePanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        setJMenuBar(menuBar);

        setMenuBarListeners();

        setLocationRelativeTo(null);
        setVisible(true);

        initConnection();

    }
    private JPanel getButtonPanel(){
        var panel = new JPanel();
        sendButton = new JButton("Wyslij");
        panel.add(sendButton);
        return panel;
    }
    private void initConnection() throws IOException {
        this.connection = new Connection(messagePanel);
    }
    private void setMenuBarListeners(){
        menuBar.getEndMenuItem().addActionListener(e -> System.exit(0));

        getSendButton().addActionListener(e -> {
            String message = JOptionPane.showInputDialog("Wyślij wiadomość: ");
            if(message != null && !message.trim().isEmpty()){
                connection.sendMessage(message);
            }
        });
    }
    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public JButton getSendButton() {
        return sendButton;
    }
}
