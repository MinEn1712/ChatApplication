package org.example;

public class ChatAuthentication {
    private final String userName;
    private final String password;

    public ChatAuthentication(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public boolean checkAuthentication(){
        return (userName.stripLeading().equals("Minh Anh")  && password.stripLeading().equals("Manh123"));
    }
}
