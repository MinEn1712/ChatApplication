package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChatGroup {
    public int groupID;
    public int groupType; //1 - 1vs1, 2 - group chat
    public String groupName;
    public List<String> groupMembers;
    public List<ChatMessage> groupMessages;

    public ChatGroup(int id, int type, String name, List<String> members){
        this.groupID = id;
        this.groupType = type;
        this.groupName = name;
        this.groupMembers = members;
        this.groupMessages = new ArrayList<>();
    }

//    public ChatGroup getChatGroup(List<ChatGroup> chatGroupList, int id){
//        for(ChatGroup chatGroup : chatGroupList){
//            if(chatGroup.groupID == id){
//                return chatGroup;
//            }
//        }
//        return null;
//    }

    public ChatGroup getPrivateChat(List<ChatGroup> chatGroupList, String name){
        for(ChatGroup chatGroup : chatGroupList){
            if(chatGroup.groupType == 1 && chatGroup.groupName.equals(name)){
                return chatGroup;
            }
        }
        return null;
    }

    public ChatGroup getGroupChat(List<ChatGroup> chatGroupList, String name){
        for(ChatGroup chatGroup : chatGroupList){
            if(chatGroup.groupType == 2 && chatGroup.groupName.equals(name)){
                return chatGroup;
            }
        }
        return null;
    }
}
