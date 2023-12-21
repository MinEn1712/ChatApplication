package org.example.server;

import java.net.ServerSocket;

public class Main {
    public static ChatServerController serverController;
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1234);
        serverController = new ChatServerController(serverSocket);
        serverController.serverStart();
    }
}
