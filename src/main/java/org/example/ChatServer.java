package org.example;

//import com.mysql.cj.xdevapi.Client;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private final ServerSocket serverSocket;

    public ChatServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void serverStart(){
        try{
            System.out.println("Waiting for connection.");

            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("New Friend Connected");

                ClientManager clientManager = new ClientManager(socket);

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

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        ChatServer server = new ChatServer(serverSocket);
        server.serverStart();
    }
}
