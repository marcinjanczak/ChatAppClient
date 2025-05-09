package com.chatappClient.views.panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class MessagePanel extends JPanel {
    private ArrayList<String> messageArray;
    JTextArea messageTextArea;
    public MessagePanel (){
        setLayout(new BorderLayout());

        messageArray = new ArrayList<>();

        messageArray.add("JD");
        messageArray.add("JD");
        messageArray.add("JD");
        messageArray.add("JD");

        messageTextArea = new JTextArea(15,40);
        messageTextArea.setEditable(false);
        messageTextArea.setMargin(new Insets(10,10,10,10));

        updateMessageTextArea();

        JScrollPane jScrollPane = new JScrollPane(messageTextArea);

        jScrollPane.setPreferredSize(new Dimension(600,500));
        add(jScrollPane,BorderLayout.CENTER);
    }
    public void updateMessageTextArea(){
        StringBuilder sb = new StringBuilder();
        for (String message : messageArray){
            sb.append(message).append('\n');
        }
        messageTextArea.setText(sb.toString());
        messageTextArea.setCaretPosition(0);
    }
}
