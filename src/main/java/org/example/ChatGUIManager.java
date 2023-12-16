package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ChatGUI extends JPanel implements ActionListener{
    protected String userName;
    protected String password;
    protected String actionType;
    protected JFrame frame = new JFrame();
    protected JPanel panel = new JPanel();
    public ChatGUI(){

    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getActionType() {
        return actionType;
    }
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void createPanel(){}
    public void createFrame(boolean isXAxis){
        frame.setTitle("Java Chat");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(isXAxis){
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        }
        else{
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        }

        createPanel();

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

    }
}
class ChatMessageGUI extends ChatGUI{
    private static final JButton sendButton = new JButton("Send");
    String text;
    @Override public void createPanel(){
        JPanel activeUsersSection = new JPanel();
        JPanel chatMessageSection = new JPanel();
        JPanel chatInputPanel = new JPanel();

        JLabel activeUsersLabel = new JLabel("Friend List");

        JTextArea chatInputField = new JTextArea(2, 50);
        JTextArea chatOutputArea = new JTextArea(30, 70);
        JScrollPane scroller = new JScrollPane(chatOutputArea);

        JButton addFileButton = new JButton("Add File");
        JButton sendButton = new JButton("Send");
        //sendButton.addActionListener(this);
        sendButton.setActionCommand("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = chatInputField.getText();
                chatInputField.setText("");
                chatOutputArea.setText("");
                chatOutputArea.append(text);
            }
        });

        chatOutputArea.setEditable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        activeUsersSection.add(activeUsersLabel);
        activeUsersSection.add(Box.createRigidArea(new Dimension(150,0)));

        chatInputPanel.add(addFileButton);
        chatInputPanel.add(Box.createRigidArea(new Dimension(45,0)));
        chatInputPanel.add(chatInputField);
        chatInputPanel.add(Box.createRigidArea(new Dimension(5,0)));
        chatInputPanel.add(sendButton);

        chatMessageSection.add(scroller);
        chatMessageSection.add(chatInputPanel);

        panel.add(activeUsersSection);
        panel.add(chatMessageSection);
    }
}

class SignUpGUI extends ChatGUI{
    protected String confirmPass;
    @Override public void createPanel(){
        JLabel label = new JLabel();
        label.setText("Register");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 60));
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,50)));

        JPanel userNamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel confirmPasswordPanel = new JPanel();
        JPanel signUpPanel = new JPanel();
        JPanel loginPanel = new JPanel();

        JLabel userNameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel confirmPasswordLabel = new JLabel("Confirm password");
        JLabel loginLabel = new JLabel("Already have an account?");

        JTextField userNameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);
        JPasswordField confirmPasswordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        signUpButton.setPreferredSize(new Dimension(210, 25));
        signUpButton.setActionCommand("signup");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = userNameField.getText();
                password = passwordField.getText();
                confirmPass = confirmPasswordField.getText();
                actionType = e.getActionCommand();
            }
        } );

        userNamePanel.add(userNameLabel);
        userNamePanel.add(Box.createRigidArea(new Dimension(54,0)));
        userNamePanel.add(userNameField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(Box.createRigidArea(new Dimension(57,0)));
        passwordPanel.add(passwordField);

        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(Box.createRigidArea(new Dimension(10,0)));
        confirmPasswordPanel.add(confirmPasswordField);

        signUpPanel.add(signUpButton);
        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                LoginGUI loginGUI = new LoginGUI();
                loginGUI.createFrame(false);
            }
        } );

        panel.add(userNamePanel);
        panel.add(passwordPanel);
        panel.add(confirmPasswordPanel);
        panel.add(signUpPanel);
        panel.add(loginPanel);
        panel.add(Box.createRigidArea(new Dimension(0,400)));
    }
}

class LoginGUI extends ChatGUI{
    @Override public void createPanel(){
        JLabel label = new JLabel();
        label.setText("Java Chat");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 60));
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,50)));

        JPanel userNamePanel = new JPanel();
        JPanel passwordPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel signUpPanel = new JPanel();

        JLabel userNameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        JLabel signUpLabel = new JLabel("Don't have an account?");

        JTextField userNameField = new JTextField(15);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        loginButton.setPreferredSize(new Dimension(210, 25));
        loginButton.setActionCommand("login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = userNameField.getText();
                password = passwordField.getText();
                actionType = e.getActionCommand();
            }
        } );

        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        loginPanel.add(loginButton);
        signUpPanel.add(signUpLabel);
        signUpPanel.add(signUpButton);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUpGUI signUpGUI = new SignUpGUI();
                signUpGUI.createFrame(false);
            }
        } );

        panel.add(userNamePanel);
        panel.add(passwordPanel);
        panel.add(loginPanel);
        panel.add(signUpPanel);
        panel.add(Box.createRigidArea(new Dimension(0,400)));
    }
    public void closeFrame(){
        frame.dispose();
    }
}

public class ChatGUIManager {
    public String email, userName;
    public String password;
    public String actionType;
    public ChatGUIManager(){
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.createFrame(false);

        actionType = loginGUI.getActionType();
        userName = loginGUI.getUserName();
        password = loginGUI.getPassword();

        while(actionType == null){
            userName = loginGUI.getUserName();
            password = loginGUI.getPassword();
            if(!(userName != null && !userName.trim().isEmpty()) || !(password != null && !password.trim().isEmpty())) continue;
            actionType = loginGUI.getActionType();
        }

        loginGUI.closeFrame();
        getMessageGUI();
    }
    public void getMessageGUI(){
        ChatMessageGUI chatMessageGUI = new ChatMessageGUI();
        chatMessageGUI.createFrame(true);
    }
    public void readMessageGUI(){

    }
    public void sendMessageGUI(){}


    public String getActionType() {
        return actionType;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
