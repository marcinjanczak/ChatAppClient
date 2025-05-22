package com.chatappClient.views.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigDialog extends JDialog {
    private JPanel mainPanel;
    private JPanel buttonPanel;

    private JTextField ipAdressField;
    private JTextField portField;
    private JTextField nickField;

    private JButton confirmConfigButton;
    private JButton clearFieldButton;

    public ConfigDialog(JFrame parent){
        super(parent,"Skonfiguruj",true);

        setSize(350,200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(20,20));


        mainPanel = getMainPanel();
        buttonPanel = getButtonPanel();
        add(mainPanel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
    }
    private JPanel getMainPanel(){
        var panel = new JPanel(new GridLayout(3,2,10,10));
        ipAdressField = new JTextField();
        portField =  new JTextField();
        nickField = new JTextField();

        panel.add(new JLabel("Podaj adress Ip serwera"));
        panel.add(ipAdressField);
        panel.add(new JLabel("Podaj port serwera."));
        panel.add(portField);
        panel.add(new JLabel("Podaj swój nick"));
        panel.add(nickField);

        return panel;
    }
    private JPanel getButtonPanel(){
        var panel = new JPanel(new GridLayout(1,2,10,10));
        confirmConfigButton = new JButton("Zatwierdź");
        clearFieldButton = new JButton("Wyczyść pola");

        panel.add(confirmConfigButton);
        panel.add(clearFieldButton);

        clearFieldButton.addActionListener(actionEvent -> {
            ipAdressField.setText("");
            portField.setText("");
            nickField.setText("");
        });

        confirmConfigButton.addActionListener(actionEvent -> {

            dispose();
        });
        return panel;
    }
    public Map<String,String> setNewConfigMap(){
        Map<String,String> configMap = new HashMap<>();
        configMap.put("adressip",parseFields(ipAdressField));
        configMap.put("port",parseFields(portField));
        configMap.put("nick",parseFields(nickField));

        return configMap;
    }
    private String parseFields(JTextField field){
        return field.getText();
    }

}
