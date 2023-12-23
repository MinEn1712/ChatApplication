package org.example.client;

import javax.swing.*;
import java.awt.*;

public class ChatMessageGUI extends ChatGUI{
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
