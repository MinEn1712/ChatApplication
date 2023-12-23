package org.example.server;

import java.util.List;

public class ChatGroup {
    public String groupName;
    public List<String> groupMembers;

    public ChatGroup(String name, List<String> members){
        this.groupName = name;
        this.groupMembers = members;
    }
}
