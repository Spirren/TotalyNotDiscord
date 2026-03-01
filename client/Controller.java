//send message to database
//update UI
package client;

import client.appinterface.Interface;
import client.appinterface.view.ChatWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.model.LoginRequest;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IChat;
import resources.model.types.OperationType;
import resources.sockets.ChatClient;
import resources.model.TextMessage;

public class Controller{ //implements "MessageListener"
    private final Interface ui;
    private final ChatWindow chatWindow;
    private ChatClient client;

    public Controller(Interface ui, ChatClient client){
        this.ui = ui;
        chatWindow = ui.getChatWindow();
        this.client = client;

        //loadChats
        // loadChats();

        //sidebar listener
        ui.getChatList().addListSelectionListener(new ChatSelectionListener());

        //button listener
        chatWindow.getSendButton().addActionListener(new SendListener(chatWindow.getIndex(), client));
        chatWindow.getLoginButton().addActionListener(new LoginListener());
    }

    // private void loadChats(){
    //     //show chats for a user in sidebar
    // }

    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String uname = chatWindow.getLogin();
            LoginRequest login = new LoginRequest(uname, "password");//string, string
            client.send(new DispatchRequest(login, OperationType.LOGIN));
        }
    }

    class SendListener implements ActionListener{
        private int index;
        //private ChatClient client;
        public SendListener(int index, ChatClient client){
            this.index=index;
            //this.client=client;
        }
        
        @Override
        public void actionPerformed(ActionEvent e){
                String input = chatWindow.getMsg();
                TextMessage msg = new TextMessage(LocalDateTime.now(), input, client.getUser(), ++index+8, chatWindow.getChat().getChatId());
                client.send(new DispatchRequest(msg, OperationType.ADD));

                //hampus fix this prob
                //chatWindow.updateView(chatWindow.getChat());
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