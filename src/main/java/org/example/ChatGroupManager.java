package org.example;

import java.util.ArrayList;
import java.util.List;

public class ChatGroupManager {
    public static int initialGroupID = 1;
    int groupID;
    String groupName;
    List<String> groupMembers = new ArrayList<>();

    public ChatGroupManager(String name, List<String> members){
        this.groupID = initialGroupID++;
        this.groupName = name;
        this.groupMembers = members;
    }
}
