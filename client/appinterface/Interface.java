package client.appinterface;

import client.appinterface.view.ChatWindow;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import resources.model.interfaces.IChat;
import resources.model.interfaces.ISidebarUpdateListener;

public class Interface extends JFrame implements ISidebarUpdateListener{
    private final ChatWindow chatWindow;
    private final JList<IChat> chatList;
    private final DefaultListModel<IChat> listModel;
    
    public Interface(){
        setTitle("TotalyNotDiscord");
        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //side bar setup
        listModel = new DefaultListModel<>();
        chatList = new JList<>(listModel);
        chatList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane sidebarScroll = new JScrollPane(chatList);
        sidebarScroll.setPreferredSize(new Dimension(150, 0));

        chatWindow = new ChatWindow();

        add(sidebarScroll, BorderLayout.WEST);
        add(chatWindow, BorderLayout.CENTER);
        setVisible(true);
    }
    
    public ChatWindow getChatWindow(){
        return chatWindow;
    }

    public JList<IChat> getChatList() { 
        return chatList; 
    }

    public DefaultListModel<IChat> getListModel() { 
        return listModel; 
    }

    @Override
    public void updateSideBar(ArrayList<IChat> chats){
        listModel.removeAllElements();

        for (IChat chat : chats) {
            listModel.addElement(chat);
        }
    }

}
