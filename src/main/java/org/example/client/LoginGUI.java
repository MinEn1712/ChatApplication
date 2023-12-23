package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends ChatGUI{
    JPanel userNamePanel = new JPanel();    JPanel passwordPanel = new JPanel();
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
