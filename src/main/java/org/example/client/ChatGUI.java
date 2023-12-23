package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatGUI extends JPanel implements ActionListener{
    public String userName;
    public String password;
    public String actionType;
    public JFrame frame = new JFrame();
    public JPanel panel = new JPanel();
    public ChatGUI(){
        this.userName = "";
        this.password = "";
        this.actionType = "";
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

    public void createPanel(){}
    public void createFrame(){
        frame.setTitle("Java Chat");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPanel();

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

    }
}
class ChatMessageGUI extends ChatGUI{
    JPanel activeUsersSection = new JPanel();
    JPanel activeUserPanel = new JPanel();
    JPanel activeUsersListPanel = new JPanel();
    JPanel groupListPanel = new JPanel();
    JPanel chatMessageSection = new JPanel();
    JPanel chatInputPanel = new JPanel();

    JLabel activeUsersLabel = new JLabel("Friend List");
    JLabel groupLabel = new JLabel("Group List");
    JLabel userLabel = new JLabel();

    JTextField chatInputField = new JTextField( 52);
    JTextArea chatOutputArea = new JTextArea(30, 70);
    JScrollPane scroller = new JScrollPane(chatOutputArea);
    JButton addFileButton = new JButton("Add File");
    JButton sendButton = new JButton("Send");
    JButton createGroupButton = new JButton("Create group");

    JList<String> activeUsersList;
    JList<String> groupLists;
    DefaultListModel<String> activeUsers = new DefaultListModel<>();
    DefaultListModel<String> groups = new DefaultListModel<>();
    public ChatMessageGUI(String userName){
        this.userName = userName;
    }
    @Override public void createPanel(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        sendButton.setActionCommand("send");

        chatOutputArea.setEditable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        userLabel.setText("Username: " + userName);
        userLabel.setFont(new Font("Serif", Font.BOLD, 15));

        JScrollPane sp = new JScrollPane(activeUsersList);
        activeUsersList = new JList<>(activeUsers);
        activeUsersList.setPreferredSize(new Dimension(150, 205));
        activeUsersList.setBorder(BorderFactory.createLineBorder(Color.black));
        activeUsersListPanel.add(activeUsersList);

        groupLists = new JList<>(groups);
        groupLists.setPreferredSize(new Dimension(150, 205));
        groupLists.setBorder(BorderFactory.createLineBorder(Color.black));
        groupListPanel.add(groupLists);

        activeUserPanel.setLayout(new BoxLayout(activeUserPanel, BoxLayout.PAGE_AXIS));
        activeUserPanel.setAlignmentX(CENTER_ALIGNMENT);
        userLabel.setAlignmentX(CENTER_ALIGNMENT);
        activeUsersLabel.setAlignmentX(CENTER_ALIGNMENT);
        groupLabel.setAlignmentX(CENTER_ALIGNMENT);
        createGroupButton.setAlignmentX(CENTER_ALIGNMENT);

        activeUserPanel.add(userLabel);
        activeUserPanel.add(activeUsersLabel);
        activeUserPanel.add(activeUsersListPanel);
        activeUserPanel.add(groupLabel);
        activeUserPanel.add(groupListPanel);
        activeUserPanel.add(createGroupButton);

        activeUsersSection.add(activeUserPanel);
        activeUsersSection.add(Box.createRigidArea(new Dimension(50,0)));

        chatInputPanel.add(addFileButton);
        chatInputPanel.add(Box.createRigidArea(new Dimension(10,0)));
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
    public String confirmPass;
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
    public String getConfirmPass(){
        return this.confirmPass;
    }
    @Override public void createPanel(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText("Register");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 60));
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,50)));

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

        loginButton.setActionCommand("signinToLogin");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

        panel.add(userNamePanel);
        panel.add(passwordPanel);
        panel.add(confirmPasswordPanel);
        panel.add(signUpPanel);
        panel.add(loginPanel);
        panel.add(Box.createRigidArea(new Dimension(0,400)));
    }
    public void closeFrame(){
        frame.dispose();
    }
}

class LoginGUI extends ChatGUI{
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
    @Override public void createPanel(){
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel();
        label.setText("Java Chat");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 60));
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,50)));

        loginButton.setPreferredSize(new Dimension(210, 25));
        loginButton.setActionCommand("login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userName = userNameField.getText();
                password = passwordField.getText();
                actionType = e.getActionCommand();
            }
        } );

        signUpButton.setActionCommand("loginToSignup");
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
