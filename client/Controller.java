//send message to database
//update UI
package client;

import client.appinterface.Interface;
import client.appinterface.view.ChatWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.model.interfaces.IChat;

public class Controller{ //implements "MessageListener"
    private final Interface ui;
    private final ChatWindow chatWindow;

    public Controller(){
        ui = new Interface();
        chatWindow = ui.getChatWindow();

        //loadChats
        loadChats();

        //sidebar listener
        ui.getChatList().addListSelectionListener(new ChatSelectionListener());

        //button listener
        chatWindow.getSendButton().addActionListener(new SendListener());
    }

    private void loadChats(){
        //show chats for a user in sidebar
    }

    class SendListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){

                //send to database - hampus&emily metod?/alfred metod?
                chatWindow.updateView(chatWindow.getChat());
        }
    }

    //when user selects a new chat from sidebar
    class ChatSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                IChat selectedChat = ui.getChatList().getSelectedValue();
                if (selectedChat != null) {
                    // Update ChatWindow with new chat
                    ui.getChatWindow().setChat(selectedChat);
                }
            }
        }
    }
}