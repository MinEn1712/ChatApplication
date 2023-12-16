package org.example;

public class ChatMessage {
    String messageSender;
    String messageType;
    String messageContent;

    public ChatMessage(String sender, String type, String content){
        this.messageSender = sender;
        this.messageType = type;
        this.messageContent = content;
    }
}
