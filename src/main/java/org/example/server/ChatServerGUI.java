package org.example.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatServerGUI extends JPanel implements ActionListener {
    public String ipAddress;
    public int portNumber;
    public JFrame frame = new JFrame();
    public JPanel panel = new JPanel();
    public JPanel receiveMessageAreaPanel = new JPanel();
    public JPanel onlineUsersPanel = new JPanel();
    public JTextArea receiveMessageArea = new JTextArea(18, 25);
    public JTextArea onlineUsersField = new JTextArea();
    public ChatServerGUI(int port){
        this.ipAddress = "localhost";
        this.portNumber = port;
    }
    public void createFrame(){
        frame.setTitle("Java Chat Server");
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Java Chat Server");
        JLabel ipAddressLabel = new JLabel("IP Address: " + ipAddress);
        JLabel portLabel = new JLabel("Port: " + portNumber);
        JLabel onlineUsersLabel = new JLabel("Online users: ");

        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        ipAddressLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ipAddressLabel.setFont(new Font("San Serif", Font.BOLD, 14));

        portLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        portLabel.setFont(new Font("San Serif", Font.BOLD, 14));

        onlineUsersLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        onlineUsersLabel.setFont(new Font("San Serif", Font.BOLD, 14));
        onlineUsersField.setEditable(false);
        onlineUsersPanel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        onlineUsersPanel.add(onlineUsersLabel);
        onlineUsersPanel.add(onlineUsersField);

        JScrollPane scroller = new JScrollPane(receiveMessageArea);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        receiveMessageArea.setEditable(false);

        receiveMessageArea.setBorder(BorderFactory.createLineBorder(Color.black));
        receiveMessageAreaPanel.add(receiveMessageArea);

        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0,10)));
        panel.add(ipAddressLabel);
        panel.add(portLabel);
        panel.add(onlineUsersPanel);
        panel.add(receiveMessageAreaPanel);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){

    }
    public void closeFrame(){
        frame.dispose();
    }
}
