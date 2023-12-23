package org.example.client;

import java.util.ArrayList;
import java.util.List;

public class ChatMessage {
    String messageReceiver;
    List<String> messageContent;

    public ChatMessage(String receiver){
        this.messageReceiver = receiver;
        this.messageContent = new ArrayList<>();
    }
}
