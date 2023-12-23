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

