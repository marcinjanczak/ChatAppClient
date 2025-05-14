package com.chatappClient.views;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final JMenu fileMenu;

    private final JMenuItem endMenuItem;
    private final JMenuItem configMenuItem;

    public MenuBar(){
        fileMenu = new JMenu("File");
        configMenuItem = new JMenuItem("Konfiguracja");

        endMenuItem = new JMenuItem("Zakończ");

        add(fileMenu);

        fileMenu.add(configMenuItem);
        fileMenu.add(endMenuItem);
    }

    public JMenuItem getEndMenuItem() {

        return endMenuItem;
    }

    public JMenuItem getConfigMenuItem() {
        return configMenuItem;
    }
}
