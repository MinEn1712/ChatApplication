package org.example.client;

public class ChatMessage {
    String messageSender;
    String messageType;
    String messageContent;

    public ChatMessage(){}
    public ChatMessage(String sender, String type, String content){
        this.messageSender = sender;
        this.messageType = type;
        this.messageContent = content;
    }
}
