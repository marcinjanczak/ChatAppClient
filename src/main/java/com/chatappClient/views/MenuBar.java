package com.chatappClient.views;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private final JMenu fileMenu;
    private final JMenuItem endMenuItem;
    public MenuBar(){
        fileMenu = new JMenu("File");

        endMenuItem = new JMenuItem("Zakończ");

        add(fileMenu);
        fileMenu.add(endMenuItem);
    }

    public JMenuItem getEndMenuItem() {
        return endMenuItem;
    }
}
