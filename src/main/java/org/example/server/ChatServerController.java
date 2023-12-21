package org.example.server;

//import com.mysql.cj.xdevapi.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerController {
    ServerSocket serverSocket;
    public List<ChatClient> chatClients;
    public List<ChatGroup> chatGroups;

    public ChatServerController(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.chatClients = new ArrayList<>();
        this.chatGroups = new ArrayList<>();
    }

    public void serverStart(){
        try{
            System.out.println("Waiting for connection.");

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("New Friend Connected");

                ChatClientManager clientManager = new ChatClientManager(socket);

                Thread thread = new Thread(clientManager);
                thread.start();
            }
        } catch (IOException e){
            System.out.println("There is an error!");
        }
    }
    public void closerServer(){
        try{
            if(serverSocket != null){
                serverSocket.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
