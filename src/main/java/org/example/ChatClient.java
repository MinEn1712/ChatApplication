package org.example;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//class Message{
//    String messageSender;
//    String messageReceiver;
//    String messageContent;
//
//    public Message(){
//
//    }
//    public String getMessageSender() {
//        return messageSender;
//    }
//    public String getMessageReceiver() {
//        return messageReceiver;
//    }
//    public String getMessageContent() {
//        return messageContent;
//    }
//}
//
//class Group{
//    int groupID;
//    String groupName;
//    List<String> groupMembers = new ArrayList<>();
//    List<Message> groupMessages = new ArrayList<>();
//
//}
public class ChatClient{
    private Socket socket;
    private BufferedReader receiver;
    private BufferedWriter writer;
    private String userName;

    public ChatClient(Socket socket, String name){
        try{
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = name;

        }
        catch (IOException e){
            closeAll(socket, receiver, writer);
        }
    }
    public void sendMessage(){
        try{
            writer.write(userName);
            writer.newLine();
            writer.flush();

            Scanner sc = new Scanner(System.in);

            while(socket.isConnected()){
                String messageToSend = sc.nextLine();
                writer.write(userName + ": " + messageToSend);
                writer.newLine();
                writer.flush();

            }
        } catch(IOException e){
            closeAll(socket, receiver, writer);

        }
    }
    public void readMessage(){
        new Thread( new Runnable() {

            @Override
            public void run() {
                String msfFromGroupChat;

                while(socket.isConnected()){
                    try{
                        msfFromGroupChat = receiver.readLine();
                        System.out.println(msfFromGroupChat);
                    } catch (IOException e){
                        closeAll(socket, receiver, writer);
                    }

                }

            }

        }).start();
    }
    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter){
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

    public static void main(String[] args) throws UnknownHostException, IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        Socket socket = new Socket("localhost", 1234);
        ChatClient client = new ChatClient(socket, name);
        client.readMessage();
        client.sendMessage();
    }
}
