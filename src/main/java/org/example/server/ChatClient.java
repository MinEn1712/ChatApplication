package org.example.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatClient {
    public Socket socket;
    public BufferedReader receiver;
    public BufferedWriter writer;
    public String userName;
    public List<String> onlineUsers = new ArrayList<>();
    public ChatClient(){

    }
    public ChatClient(String userName, Socket socket, BufferedReader receiver, BufferedWriter writer) {
        this.userName = userName;
        this.socket = socket;
        this.receiver = receiver;
        this.writer = writer;
        this.onlineUsers = new ArrayList<>();
    }
}
