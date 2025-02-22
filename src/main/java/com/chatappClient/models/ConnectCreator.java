package com.chatappClient.models;

import java.util.Scanner;

public class ConnectCreator {
   Scanner scanner = new Scanner(System.in);
    private String ipAddress, userNick;
    private int port;


    public ConnectCreator(){
        System.out.println("Witaj w aplikacji Chatowej");
        this.ipAddress = setipAddress();
        System.out.println("Podaj port z którym się łączysz");
        this.port = setPort();
        System.out.println("Podaj swój nick");
        this.userNick = scanner.nextLine();
    }
    private String setipAddress(){
        System.out.println("Podaj adres z którym chcesz się połączyć (default: localhost)");
        var adress = scanner.nextLine();
        if (adress.isEmpty()){
            adress = "localhost";
        }
        return adress;
    }
    private Integer setPort(){
        System.out.println("Podaj port do z którym się łączysz (default: 12345)");
        var port = scanner.nextLine();
        if(port.isEmpty()){
            port = "12345";
        }
        return Integer.parseInt(port);
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public int getPort() {
        return port;
    }
}
