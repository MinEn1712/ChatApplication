package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//class GroupManager{
//    int groupID;
//    String groupName;
//    List<String> groupMembers = new ArrayList<>();
//}

class ClientManager implements Runnable{
    public static ArrayList<ClientManager> clientManagers = new ArrayList<>();
    String userName;
    Socket socket;
    BufferedReader receiver;
    BufferedWriter writer;

    public ClientManager(Socket socket){
        try{
            this.socket = socket;
            this.writer = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
            this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = receiver.readLine();
            clientManagers.add(this);
            broadcastMessage("SERVER" + userName + " has entered in the room");

        } catch(IOException e){
            closeAll(socket, receiver, writer);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()){
            try{
                messageFromClient = receiver.readLine();
                broadcastMessage(messageFromClient);
            } catch(IOException e){
                closeAll(socket, receiver,  writer);
                break;
            }
        }
    }

    public void broadcastMessage(String messageToSend){
        for(ClientManager clientManager: clientManagers){
            try{
                if(!clientManager.userName.equals(userName)){
                    clientManager.writer.write(messageToSend);
                    clientManager.writer.newLine();
                    clientManager.writer.flush();
                }
            } catch(IOException e){
                closeAll(socket,receiver, writer);

            }
        }
    }
    // notify if the user left the chat
    public void removeClientHandler(){
        clientManagers.remove(this);
        broadcastMessage("server " + userName + " has gone");
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter){
        removeClientHandler();
        try{
            if(buffReader!= null){
                buffReader.close();
            }
            if(buffWriter != null){
                buffWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        } catch (IOException e){
            e.getStackTrace();
        }
    }
}

public class ChatServer {
    private final ServerSocket serverSocket;

    public ChatServer(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void serverStart(){

        try{
            // check and loop the serverSocket
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("New Friend Connected");

                ClientManager clientHandler = new ClientManager(socket);

                Thread thread = new Thread(clientHandler);
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
