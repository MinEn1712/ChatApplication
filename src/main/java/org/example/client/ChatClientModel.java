package org.example.client;

import java.util.ArrayList;
import java.util.List;

public class ChatClientModel {
    private String actionType;
    private String userName;
    private String password;

    public ChatClientModel() {}

    public ChatClientModel(String actionType, String userName, String password) {
        this.actionType = actionType;
        this.userName = userName;
        this.password = password;
    }

    public String getActionType() {
        return actionType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
