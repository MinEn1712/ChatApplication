package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends ChatGUI{
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
