package org.example.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
        StringBuilder getOnlineUser = new StringBuilder();
        Main.serverController.chatClients.add(this.chatClient);

        getOnlineUser.append("/user`");
        for (ChatClient client : Main.serverController.chatClients) {
            onlineUserList = getOnlineUser.append(client.userName).append("`").toString();
        }
        broadCastOnlineUserList(onlineUserList);

        while (chatClient.socket.isConnected()) {
            try {
                messageFromClient = chatClient.receiver.readLine();
                if(messageFromClient.contains("/send")){
                    String[] buffer = messageFromClient.split("`");
                    broadCastMessage(buffer[2], buffer[1]);
                }
                else if(messageFromClient.contains("/createGroup")){
                    String[] buffer = messageFromClient.split("`");
                    String groupName = buffer[1];

                    List<String> groupMembers = new ArrayList<>();
                    for(int i = 2; i < buffer.length; i++){
                        groupMembers.add(buffer[i]);
                    }

                    ChatGroup chatGroup = new ChatGroup(groupName, groupMembers);
                    broadCastGroupList(chatGroup);

                    Main.serverController.chatGroups.add(chatGroup);
                    Main.serverController.chatServerGUI.receiveMessageArea.append("Group " + groupName + " Created." + "\n");
                }
                if(messageFromClient.contains("/sendGroup")){
                    String[] buffer = messageFromClient.split("`");
                    broadCastGroupMessage(buffer[2], buffer[1]);
                }
            } catch (IOException e) {
                closeAll(chatClient.socket, chatClient.receiver, chatClient.writer);
                break;
            }
        }

        Main.serverController.chatClients.remove(this.chatClient);
        StringBuilder removeUser = new StringBuilder();
        removeUser.append("/remove`");
        if(Main.serverController.chatClients.size() == 1){
            for (ChatClient client : Main.serverController.chatClients) {
                onlineUserList = removeUser.append(client.userName).toString();
            }
        }
        else{
            for (ChatClient client : Main.serverController.chatClients) {
                onlineUserList = removeUser.append(client.userName).append("`").toString();
            }
        }
        broadCastOnlineUserList(onlineUserList);
        Main.serverController.onlineUsers--;
        Main.serverController.chatServerGUI.onlineUsersField.setText(Integer.toString(Main.serverController.onlineUsers));
        Main.serverController.chatServerGUI.receiveMessageArea.append("User " + this.chatClient.userName + " Disconnected" + "\n");
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
    }
    public void broadCastGroupMessage(String messageToSend, String groupName) {
        for (ChatGroup group : Main.serverController.chatGroups) {
            if(group.groupName.equals(groupName)) {
                for (ChatClient client : Main.serverController.chatClients) {
                    for(String member : group.groupMembers){
                        if(client.userName.equals(member)){
                            try {
                                client.writer.write("/receiveGroup`" + group.groupName + "`" + messageToSend);
                                client.writer.newLine();
                                client.writer.flush();
                            } catch (IOException e) {
                                closeAll(client.socket, client.receiver, client.writer);
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

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
public void broadCastGroupList(ChatGroup group) {
    for (ChatClient client : Main.serverController.chatClients) {
        for(String member : group.groupMembers){
            if(client.userName.equals(member)){
                try {
                    client.writer.write("/group`" + group.groupName);
                    client.writer.newLine();
                    client.writer.flush();
                } catch (IOException e) {
                    closeAll(client.socket, client.receiver, client.writer);
                    e.printStackTrace();
                }
            }
        }
    }
}
    public void closeAll(Socket socket, BufferedReader receiver, BufferedWriter writer) {
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
