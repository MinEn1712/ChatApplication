package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServerController {
    ServerSocket serverSocket;
    int onlineUsers;
    public ChatServerGUI chatServerGUI;
    public List<ChatClient> chatClients;
    public List<ChatGroup> chatGroups;

    public ChatServerController(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
        this.onlineUsers = 0;
        this.chatClients = new ArrayList<>();
        this.chatGroups = new ArrayList<>();
        this.chatServerGUI = new ChatServerGUI(serverSocket.getLocalPort());
    }

    public void serverStart(){
        try{
            chatServerGUI.createFrame();
            chatServerGUI.receiveMessageArea.append("Waiting for connection." + "\n");
            chatServerGUI.onlineUsersField.setText(Integer.toString(onlineUsers));

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                onlineUsers++;

                chatServerGUI.onlineUsersField.setText(Integer.toString(onlineUsers));
                chatServerGUI.receiveMessageArea.append("New Friend Connected." + "\n");

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
