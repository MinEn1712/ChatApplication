package org.example.server;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ChatClient {
    public Socket socket;
    public BufferedReader receiver;
    public BufferedWriter writer;
    public String userName;
    public ChatClient(){

    }
    public ChatClient(String userName, Socket socket, BufferedReader receiver, BufferedWriter writer) {
        this.userName = userName;
        this.socket = socket;
        this.receiver = receiver;
        this.writer = writer;
    }

    public static ChatClient findClient(List<ChatClient> clientList, String userName) {
        for (ChatClient client : clientList) {
            if (client.userName.equals(userName))
                return client;
        }
        return null;
    }
}
