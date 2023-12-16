package org.example;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientManager implements Runnable{
    public static List<ClientManager> clientManagers = new ArrayList<>();
    public static List<ChatGroup> chatGroups = new ArrayList<>();
    private Socket socket;
    private BufferedReader receiver;
    private BufferedWriter writer;
    private String userName;

    public ClientManager(Socket socket) {
        try {
            this.socket = socket;
            this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName = receiver.readLine();
            clientManagers.add(this);
        } catch (IOException e) {
            closeAll(socket, receiver, writer);
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        //createPrivateGroup();

        while (socket.isConnected()) {
            try {
                messageFromClient = receiver.readLine();
                broadCastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(socket, receiver, writer);
                break;
            }
        }
    }

    public void broadCastMessage(String messageToSend) {
        for(ClientManager clientManager : clientManagers){
            try{
                if (!(clientManager.userName.equals(this.userName))){
                    clientManager.writer.write(messageToSend);
                    clientManager.writer.newLine();
                    clientManager.writer.flush();
                }
            }
            catch (IOException e) {
                closeAll(socket, receiver, writer);
            }
        }
//        if(chatGroups.size() != 0){
//            ChatGroup group = chatGroups.get(0);
//            if(!(group.groupName.equals(this.userName))){
//                for(ClientManager clientManager : clientManagers){
//                    try{
//                        if (!(clientManager.userName.equals(group.groupName))){
//                            clientManager.writer.write(messageToSend);
//                            clientManager.writer.newLine();
//                            clientManager.writer.flush();
//                        }
//                    }
//                    catch (IOException e) {
//                        closeAll(socket, receiver, writer);
//                    }
//                }
//        }
//        for (ClientManager clientHandler : clientManagers) {
//            try {
//                //if (!(clientHandler.userName.equals(this.userName)) && clientHandler.userName.equals(userName)) {
//                if (!(clientHandler.userName.equals(this.userName))) {
//                    clientHandler.writer.write(messageToSend);
//                    clientHandler.writer.newLine();
//                    clientHandler.writer.flush();
//                }
//            } catch (IOException e) {
//                closeAll(socket, receiver, writer);
//            }
//        }
    }

    public void createPrivateGroup() {
        for (ClientManager clientManager : clientManagers) {
            if (!(clientManager.userName.equals(this.userName))) {
                List<String> members = new ArrayList<>();
                members.add(this.userName);
                members.add(clientManager.userName);

                ChatGroup group = new ChatGroup(1, 1, clientManager.userName, members);
                chatGroups.add(group);
            }
        }
    }

    public void removeClientHandler() {
        clientManagers.remove(this);
//        for (ChatGroup chatGroup : chatGroups){
//            for(String member : chatGroup.groupMembers){
//                if(member == this.userName){
//                    chatGroups.remove(chatGroup);
//                }
//            }
//        }
        //broadCastMessage("SERVER: " + userName + " has left the chat!");
    }

    public void closeAll(Socket socket, BufferedReader receiver, BufferedWriter writer) {
        removeClientHandler();
        try {
            if (receiver != null) {
                receiver.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
