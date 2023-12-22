package org.example.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatClientManager extends Thread{
    public ChatClient chatClient;
    public ChatClientManager(Socket socket) {
        try {
            chatClient = new ChatClient();
            chatClient.socket = socket;
            chatClient.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            chatClient.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            chatClient.userName = chatClient.receiver.readLine();
        } catch (IOException e) {
            //closeAll(socket, receiver, writer);
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageFromClient;
        String onlineUserList = "";
        StringBuilder sb = new StringBuilder();
        Main.serverController.chatClients.add(this.chatClient);

        sb.append("/user`");
        for (ChatClient client : Main.serverController.chatClients) {
            onlineUserList = sb.append(client.userName).append("`").toString();
        }
        broadCastOnlineUserList(onlineUserList);

        while (chatClient.socket.isConnected()) {
            try {
                messageFromClient = chatClient.receiver.readLine();
                if(messageFromClient.contains("`")){
                    String[] buffer = messageFromClient.split("`");
                    broadCastMessage(buffer[1], buffer[0]);
                }
                //broadCastMessage(messageFromClient);
            } catch (IOException e) {
                closeAll(chatClient.socket, chatClient.receiver, chatClient.writer);
                break;
            }
        }

        Main.serverController.chatClients.add(this.chatClient);

        for (ChatClient client : Main.serverController.chatClients) {
            client.onlineUsers.add(chatClient.userName);
        }
    }

    public void broadCastMessage(String messageToSend, String messageReceiver) {
        for (ChatClient client : Main.serverController.chatClients) {
            try {
                if (client.userName.equals(messageReceiver)) {
                    client.writer.write(messageToSend);
                    client.writer.newLine();
                    client.writer.flush();
                }
            } catch (IOException e) {
                closeAll(client.socket, client.receiver, client.writer);
                e.printStackTrace();
            }
        }
//        for (ChatClient client : Main.serverController.chatClients) {
//            try {
//                if (!(client.userName.equals(this.chatClient.userName))) {
//                    client.writer.write(messageToSend);
//                    client.writer.newLine();
//                    client.writer.flush();
//                }
//            } catch (IOException e) {
//                //closeAll(socket, receiver, writer);
//                e.printStackTrace();
//            }
//        }
    }




//        if (chatGroups.size() != 0) {
//            ChatGroup group = chatGroups.get(0);
//            if (!(group.groupName.equals(this.userName))) {
//                for (ChatClientManager clientManager : clientManagers) {
//                    try {
//                        if (!(clientManager.userName.equals(group.groupName))) {
//                            clientManager.writer.write(messageToSend);
//                            clientManager.writer.newLine();
//                            clientManager.writer.flush();
//                        }
//                    } catch (IOException e) {
//                        //closeAll(socket, receiver, writer);
//                    }
//                }
//            }



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
//        }
//    }

//    public void createPrivateGroup() {
//        for (ChatClientManager clientManager : clientManagers) {
//            if (!(clientManager.userName.equals(this.userName))) {
//                List<String> members = new ArrayList<>();
//                members.add(this.userName);
//                members.add(clientManager.userName);
//
//                ChatGroup group = new ChatGroup(1, 1, this.userName, members);
//                chatGroups.add(group);
//            }
//        }
//    }

//    public void removeClientHandler() {
//        clientManagers.remove(this);
//        for (ChatGroup chatGroup : chatGroups){
//            for(String member : chatGroup.groupMembers){
//                if(member == this.userName){
//                    chatGroups.remove(chatGroup);
//                }
//            }
//        }
        //broadCastMessage("SERVER: " + userName + " has left the chat!");
//    }
public void broadCastOnlineUserList(String onlineUserList) {
    for (ChatClient client : Main.serverController.chatClients) {
        try {
            client.writer.write(onlineUserList);
            client.writer.newLine();
            client.writer.flush();
        } catch (IOException e) {
            closeAll(client.socket, client.receiver, client.writer);
            e.printStackTrace();
        }
    }
}
    public void closeAll(Socket socket, BufferedReader receiver, BufferedWriter writer) {
        //removeClientHandler();
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
