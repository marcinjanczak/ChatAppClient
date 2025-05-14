package com.chatappClient.models;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectCreator {
    private String ipAddress;
    private  String userNick;
    private int port;
    private final String fileConfigName = "connect.conf";

    public ConnectCreator() throws IOException {
        Map<String,String> map = readConnectConfig();
        readConfig(map);
        setConnection(map);
    }

    private Map<String,String> readConnectConfig() throws IOException {
        File configFile = new File(fileConfigName);

        Map<String,String> map = new HashMap<>();

        if(configFile.exists()){
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
        } return null;
    }
    ///  Metoda tworząca nowy plik configuracyjny;
    public void createConnectConfigFile(Map<String,String> configMap) throws IOException{
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
        System.out.println(map.get("adressip"));
        System.out.println(map.get("port"));
        System.out.println(map.get("nick"));
    }
    private void setConnection(Map<String,String> map){
        System.out.println("Witaj w aplikacji Chatowej");
        this.ipAddress = setipAddress(map.get("adressip"));
        this.port = setPort(map.get("port"));
        this.userNick = setUserNick(map.get("nick"));
    }
    private String setipAddress(String adress){
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
