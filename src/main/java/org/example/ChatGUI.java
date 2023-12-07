package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Chat extends JPanel implements ActionListener {
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    private static final JButton sendButton = new JButton("Send");
    String text;


    public Chat(String userName, String password){
        frame.setTitle("Java Chat");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel friendList = new JPanel();
        JPanel chatSection = new JPanel();
        JPanel inputPanel = new JPanel();

        JLabel friendListLabel = new JLabel("Friend List");

        JTextField inputField = new JTextField(50);
        JTextArea chatTextArea = new JTextArea(30, 70);
        JScrollPane scroller = new JScrollPane(chatTextArea);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(this);
        sendButton.setActionCommand("Send");

        chatTextArea.setEditable(false);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        inputPanel.add(inputField);
        inputPanel.add(sendButton);

        friendList.add(friendListLabel);
        chatSection.add(scroller);
        chatSection.add(inputPanel);

        panel.add(friendList);
        panel.add(chatSection);

        Chat.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = inputField.getText();
                inputField.setText("");
                chatTextArea.setText("");
                chatTextArea.append(text);
            }
        });


        frame.add(panel);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

    }
}
class SignUpGUI extends JPanel{
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    public SignUpGUI(String userName, String password){
        frame.setTitle("Java Chat");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);
        signUpPanel.add(signUpButton);
        loginPanel.add(loginLabel);
        loginPanel.add(loginButton);

        panel.add(userNamePanel);
        panel.add(passwordPanel);
        //panel.add(confirmPasswordPanel);
        panel.add(signUpPanel);
        panel.add(loginPanel);
        panel.add(Box.createRigidArea(new Dimension(0,400)));

        frame.add(panel);
        frame.setVisible(true);
    }
}
class LoginGUI extends JPanel{
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    public LoginGUI(String userName, String password){
        frame.setTitle("Java Chat");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

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

        frame.add(panel);
        frame.setVisible(true);
    }
}
public class ChatGUI{
    String userName;
    String password;
    public ChatGUI(){
        //LoginGUI loginGUI = new LoginGUI(userName, password);
        //SignUpGUI signUpGUI = new SignUpGUI(userName, password);
        Chat chat = new Chat(userName, password);
    }

//    public void createAndShowGUI(){
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        JFrame frame = new JFrame("Java Chat");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JComponent newContentPane = new ChatGUI();
//        newContentPane.setOpaque(true);
//        frame.setContentPane(newContentPane);
//
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
