package com.chatappClient.views.panels;

import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MessagePanel extends JPanel {
    Stack<String> messageStack;
    JTextArea messageTextArea;
    public MessagePanel (){
        setPreferredSize(new Dimension(200, 300));

//        messageStack = connection.getMessagesStack();

        messageStack = new Stack<>();
        messageStack.push("JD");
        messageStack.push("JD");
        messageStack.push("JD");

        messageTextArea = new JTextArea();
        messageTextArea.setEditable(false);
        updateMessageTextArea();
        add(new JScrollPane(messageTextArea), BorderLayout.CENTER);
    }
    public void updateMessageTextArea(){
        StringBuilder sb = new StringBuilder();
        for (int i = messageStack.size() - 1; i >= 0; i--) {
            sb.append(messageStack.get(i));
        }
    }
}
