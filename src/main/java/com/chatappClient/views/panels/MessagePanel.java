package com.chatappClient.views.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MessagePanel extends JPanel {
    private JTextArea messageTextArea;
    private JScrollPane scrollPane;

    public MessagePanel (){
        setLayout(new BorderLayout());
        initComponents();
    }
    private void initComponents(){
        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setMargin(new Insets(10,10,10,10));

        scrollPane = new JScrollPane(messageTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane,BorderLayout.CENTER);

//        updateMessageTextArea();

    }
    public void addMessage(String message){
        messageTextArea.append(message + "\n");
        messageTextArea.setCaretPosition(messageTextArea.getDocument().getLength());
    }
    public void claerMessages(){
        messageTextArea.setText("");
    }

}
