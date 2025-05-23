package com.chatappClient.models;

import com.chatappClient.views.MainFrame;
import com.chatappClient.views.dialogs.ConfigDialog;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectCreator {
    private String ipAddress;
    private  String userNick;
    private int port;
    private final String fileConfigName = "connect.conf";
    private final MainFrame parent;

    public ConnectCreator(MainFrame parent) throws IOException {
        this.parent = parent;
        Map<String,String> map = readConnectConfig();
        setConnection(map);
        readConfig(map);
    }

    private Map<String,String> readConnectConfig() throws IOException {
        File configFile = new File(fileConfigName);

        Map<String,String> map = new HashMap<>();

        if(configFile.exists()){
            System.out.println("Pomyślnie wczytano configurację z pliku: "+ fileConfigName);

            try(BufferedReader reader = new BufferedReader(new FileReader(fileConfigName))){
                String line;
                while ((line = reader.readLine()) != null){
                    line = line.trim();
                    if(line.isEmpty() || line.startsWith("#")){
                        continue;
                    }
                    String[] parts = line.split("=",2);
                    if(parts.length == 2){
                        String key = parts[0].trim();
                        String value  = parts[1].trim();
                        map.put(key,value);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
            }

            return map;
        }
        else {
            System.out.println("Brak pliku konfiguracyjnego.");
            JOptionPane.showMessageDialog(parent,"Brak pliku konfiguracyjnego, utwórz nowy,","Brak configu", JOptionPane.INFORMATION_MESSAGE);

            ConfigDialog dialog = new ConfigDialog(parent);
            dialog.setVisible(true);

            map = dialog.getConfigMap();

            if(!map.isEmpty()){
                createConnectConfigFile(map);
            }else {
                System.out.println("Użytkownik anulował konfiguracje.");
            }
//
//
//            map.put("adressip","localhost");
//            map.put("port","12345");
//            map.put("nick","defaultUser");

            return map;
        }
    }

    ///  Metoda tworząca nowy plik configuracyjny;
    private void createConnectConfigFile(Map<String,String> configMap) throws IOException{
        String newIpAdress = configMap.get("adressip");
        String newPort = configMap.get("port");
        String newNick = configMap.get("Nick");

        StringBuilder sb = new StringBuilder();
        sb.append("#Podaj adresIp z którym chcesz się połączyć").append("\n");
        sb.append("adressip=").append(newIpAdress).append("\n");
        sb.append("#Podaj port z którym chcesz sie połączyć").append("\n");
        sb.append("port=").append(newPort).append("\n");
        sb.append("#Podaj nick do połączenia").append("\n");
        sb.append("nick=").append(newNick);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("configtest.conf"))) {
            bufferedWriter.write(sb.toString());
        }catch (FileNotFoundException e ){
            System.err.println("Błąd" + e.getMessage());
        }
    }
    private void readConfig(Map<String, String> map){
        System.out.println("||==================");
        System.out.println("|| Wczytano konfigurację");
        System.out.println("||");
        System.out.println("|| Połączono z serwerem "+map.get("adressip")+":"+map.get("port"));
        System.out.println("||");
        System.out.println("|| Twój nick "+map.get("nick"));
        System.out.println("||");
        System.out.println("||==================");
    }
    private void setConnection(Map<String,String> map){
        System.out.println("Witaj w aplikacji Chatowej");
        this.ipAddress = setIpAddress(map.get("adressip"));
        this.port = setPort(map.get("port"));
        this.userNick = setUserNick(map.get("nick"));
    }
    private String setIpAddress(String adress){
        return adress;
    }
    private Integer setPort(String port){
        return Integer.parseInt(port);
    }
    private String setUserNick(String nick){
        return nick;
    }
    public String getIpAddress() {
        return ipAddress;
    }
    public int getPort() {
        return port;
    }
    public String getUserNick() {
        return userNick;
    }
}
