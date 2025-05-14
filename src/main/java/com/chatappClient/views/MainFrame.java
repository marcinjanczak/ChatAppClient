package com.chatappClient.views;

import com.chatappClient.models.ConnectCreator;
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
    private JTextField messageField;
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

//        messagePanel.addMessage("Testowa Wiadomość");
        buttonPanel = setButtonPanel();
        add(messagePanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        setJMenuBar(menuBar);

        setListeners();

        setLocationRelativeTo(null);
        setVisible(true);

        initConnection();

    }
    private JPanel setButtonPanel(){
        var panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        messageField = new JTextField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2,2,2,2);
        panel.add(messageField,gbc);

        sendButton = new JButton("Wyslij");
        gbc.gridx = 1;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;

        panel.add(sendButton,gbc);

        return panel;
    }
    private void initConnection() throws IOException {
        ConnectCreator connectCreator = new ConnectCreator();
//        connectCreator.

        this.connection = new Connection(messagePanel,connectCreator);
    }
    private void setListeners(){
        menuBar.getEndMenuItem().addActionListener(e -> System.exit(0));
        menuBar.getConfigMenuItem().addActionListener(e -> openConfigDialog());

        getSendButton().addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());
    }
    private void sendMessage(){
        String message = parseField(messageField);
        if(message != null && !message.trim().isEmpty()){
            connection.sendMessage(message);
            messageField.setText("");
        }
    }
    private void openConfigDialog(){
        ConfigDialog dialog = new ConfigDialog(this);
        dialog.setVisible(true);
    }
    private String parseField(JTextField field){
        return field.getText();
    }
    public JPanel getMessagePanel() {
        return messagePanel;
    }
    public JButton getSendButton() {
        return sendButton;
    }
}
