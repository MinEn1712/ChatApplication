package org.example.client;

import org.example.server.ChatClient;
import org.example.server.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class ChatClientController{
    public static LoginGUI loginGUI = new LoginGUI();
    public Socket socket;
    public BufferedReader receiver;
    public BufferedWriter writer;
    public String userName;
    public List<ChatClient> onlUsers;
    public static HashMap<String, String> userInfo = new HashMap<>();
    public ChatClientController(){

    }
    public ChatClientController(Socket socket, String userName){
        try{
            this.socket = socket;
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.userName = userName;
//            this.chatClientView = chatClientView;
        }
        catch (IOException e){
            closeAll(socket, receiver, writer);
        }
    }
//    public void messageHandler(){
//        readMessage();
//        sendMessage(chatClientView.message.messageContent);
//    }
    public static boolean checkEmpty(String input){
        return !(input != null && !input.trim().isEmpty());
    }
    public static void getUserInfo(){
        try{
            FileReader fr = new FileReader("src/main/java/org/example/client/ChatUserInfo.txt");
            BufferedReader br = new BufferedReader(fr);
            String buffer;

            while((buffer = br.readLine()) != null) {
                if (buffer.contains("`")) {
                    String[] str = buffer.split("`");
                    userInfo.put(str[0], str[1]);
                }
            }

            br.close();
            fr.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void saveUserInfo(){
        try {
            FileWriter fw = new FileWriter("src/main/java/org/example/client/ChatUserInfo.txt");
            String slangInfo;

            for (Map.Entry<String, String> info : userInfo.entrySet()) {
                String key = info.getKey();
                String value = info.getValue();
                StringBuilder sb = new StringBuilder();

                slangInfo = sb.append(key).append("`").append(value).append("\n").toString();

                fw.write(slangInfo);
            }
            fw.close();
            System.out.println("SAVED.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    public void sendMessage(ChatMessageGUI chatMessageGUI){
        try{
            writer.write(userName);
            writer.newLine();
            writer.flush();
            chatMessageGUI.sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        String message = chatMessageGUI.chatInputField.getText();
                        writer.write(userName + ": " + message);
                        writer.newLine();
                        writer.flush();
                        chatMessageGUI.chatInputField.setText("");
                        chatMessageGUI.chatOutputArea.append(message + "\n");

                    } catch(IOException ioException){
                        closeAll(socket, receiver, writer);
                    }
                }
            });

////            Scanner sc = new Scanner(System.in);
//            //String actionType;
//            String messageToSend;
//
//            while(socket.isConnected()){
////                String messageToSend = sc.nextLine();
////                writer.write(userName + ": " + messageToSend);
////                writer.newLine();
////                writer.flush();
//
//                //actionType = chatMessageGUI.actionType;
//                if(!chatMessageGUI.chatContent.equals("")){
//                    System.out.println(userName + ": " + chatMessageGUI.chatContent);
//                    writer.write(userName + ": " + chatMessageGUI.chatContent);
//                    writer.newLine();
//                    writer.flush();
//
//                    chatMessageGUI.chatContent = "";
//                }





//                if(actionType.equals("send")){
//                    System.out.println(userName + ": " + messageToSend);
//                    writer.write(userName + ": " + messageToSend);
//                    writer.newLine();
//                    writer.flush();
//
//                    chatMessageGUI.actionType = "";
//                    chatMessageGUI.chatContent = "";
//                }

//            }
        } catch(IOException e){
            closeAll(socket, receiver, writer);
        }
    }
    public void readMessage(ChatMessageGUI chatMessageGUI){
        new Thread( new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());
                    while (true) {
                        if (scanner.hasNext()) {
                            String message = scanner.nextLine();
                            chatMessageGUI.chatOutputArea.append(message + "\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//            @Override
//            public void run() {
//                String msfFromGroupChat;
//
//                while(socket.isConnected()){
//                    try{
//                        msfFromGroupChat = receiver.readLine();
//                        System.out.println(msfFromGroupChat);
////                        chatMessageGUI.chatOutputArea.append(msfFromGroupChat);
////                        chatMessageGUI.chatOutputArea.append("\n");
//                    } catch (IOException e){
//                        closeAll(socket, receiver, writer);
//                    }
//
//                }
//
//            }
//        }).start();
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
    public static void main(String[] args) throws UnknownHostException, IOException {
        //ChatClientView view = new ChatClientView();
        //String userName = guiManager.getUserName();
//        System.out.println("Enter username: ");
//        String userName;
//        Scanner scan = new Scanner(System.in);
//        userName = scan.nextLine();
        String userName = "";
        String password = "";
        String actionType = "";

        getUserInfo();
        loginGUI.createFrame();
        while(true){
            actionType = loginGUI.getActionType();
            if(actionType.equals("login")){
                userName = loginGUI.getUserName();
                password = loginGUI.getPassword();
            }

            if(actionType.equals("login") && userInfo.containsKey(userName)){
                if(password.equals(userInfo.get(userName))){
                    loginGUI.closeFrame();
                    break;
                }
                else{
                    JOptionPane.showMessageDialog(null, "Incorrect username or password", "Login fail", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(actionType.equals("login") && !checkEmpty(userName) && !checkEmpty(userName)){
                JOptionPane.showMessageDialog(null, "Incorrect username or password", "Login fail", JOptionPane.ERROR_MESSAGE);

                loginGUI.userName = "";
                loginGUI.password = "";
                loginGUI.actionType = "";

                loginGUI.userNameField.setText("");
                loginGUI.passwordField.setText("");
            }
            else if(loginGUI.actionType.equals("loginToSignup")){
                loginGUI.frame.setVisible(false);

                SignUpGUI signUpGUI = new SignUpGUI();
                signUpGUI.createFrame();

                while(true){
                    String signupActionType = signUpGUI.getActionType();
                    if(signupActionType.equals("signup")){
                        String signupUsername = signUpGUI.getUserName();
                        String signupPassword = signUpGUI.getPassword();
                        String signupConfirmPassword = signUpGUI.getConfirmPass();

                        if(userInfo.containsKey(signupUsername)){
                            signUpGUI.userName = "";
                            signUpGUI.password = "";
                            signUpGUI.confirmPass = "";
                            signUpGUI.actionType = "";

                            signUpGUI.userNameField.setText("");
                            signUpGUI.passwordField.setText("");
                            signUpGUI.confirmPasswordField.setText("");

                            JOptionPane.showMessageDialog(null, "Username has already been used", "Sign up", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else if(signupConfirmPassword.equals(signupPassword) && !checkEmpty(signupUsername) && !checkEmpty(signupPassword) && !checkEmpty(signupConfirmPassword)){
                            signUpGUI.actionType = "";
                            loginGUI.actionType = "";

                            signUpGUI.userNameField.setText("");
                            signUpGUI.passwordField.setText("");
                            signUpGUI.confirmPasswordField.setText("");

                            JOptionPane.showMessageDialog(null, "Create account successfully", "Sign up", JOptionPane.INFORMATION_MESSAGE);

                            userInfo.put(signupUsername, signupPassword);
                            saveUserInfo();
                            signUpGUI.closeFrame();
                            loginGUI.frame.setVisible(true);
                            break;
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Confirm password is incorrect", "Sign up", JOptionPane.ERROR_MESSAGE);

                            signUpGUI.userName = "";
                            signUpGUI.password = "";
                            signUpGUI.confirmPass = "";
                            signUpGUI.actionType = "";

                            signUpGUI.userNameField.setText("");
                            signUpGUI.passwordField.setText("");
                            signUpGUI.confirmPasswordField.setText("");
                        }
                    }
                    else if(signupActionType.equals("signinToLogin")){
                        signUpGUI.actionType = "";
                        loginGUI.actionType = "";

                        signUpGUI.userNameField.setText("");
                        signUpGUI.passwordField.setText("");
                        signUpGUI.confirmPasswordField.setText("");

                        signUpGUI.closeFrame();
                        loginGUI.frame.setVisible(true);
                        break;
                    }
                }
            }
        }


        Socket socket = new Socket("localhost", 1234);
        ChatClientController client = new ChatClientController(socket, userName);
        ChatMessageGUI chatMessageGUI = new ChatMessageGUI(client.userName);
        chatMessageGUI.createFrame();
        client.readMessage(chatMessageGUI);
        client.sendMessage(chatMessageGUI);
    }

//    public static void main(String[] args) throws UnknownHostException, IOException{
//        ChatClientView view = new ChatClientView();
//        ChatClientModel chatClientModel = chatClientView.getClientInput();
//        String userName = chatClientModel.getUserName();
//        //String userName = guiManager.getUserName();
////        System.out.println("Enter username: ");
////        String userName;
////        Scanner scan = new Scanner(System.in);
////        userName = scan.nextLine();
//        Socket socket = new Socket("localhost", 1234);
//        ChatClientController client = new ChatClientController(socket, userName);
//        client.readMessage();
//        client.sendMessage();
//    }
}
