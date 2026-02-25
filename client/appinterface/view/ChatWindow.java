//display messages
//input field
//send button

//innehåller ett/flera chattobj
//Behöver kolla vilken user som e "inloggad" - iterator för o kolla vilka msgs i en chatt

package client.appinterface.view;

//import resources.model.Chat;
import java.awt.*;
import java.util.Iterator;
import javax.swing.*;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;

public class ChatWindow extends JPanel { //implements Chat/messageObserver?
    private final JTextArea chatArea; 
    private final JTextField msgField;
    private final JButton sendButton;
    private IChat CurrentChat;

    private final JPanel loginPanel;
    private final JButton loginButton;
    private final JTextField loginField;

    private int index;

    //init ChatWindow
    public ChatWindow(){
        //LoginPanel
        loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Login");
        loginField = new JTextField(10);
        loginPanel.add(loginButton);
        loginPanel.add(loginField);

        index=0;
        //Main Area
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(chatArea); 

        JPanel bottomPanel = new JPanel(new BorderLayout());

        msgField = new JTextField();
        sendButton = new JButton("Send");

        bottomPanel.add(msgField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(loginPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public int getIndex(){
        return index;
    }

    public JButton getLoginButton(){
        return loginButton; 
    }

    public String getLogin(){
        return loginField.getText();
    }

    //getsendb
    public JButton getSendButton(){
        return sendButton;
    }
    
    //getmsg
    public String getMsg(){
        return msgField.getText();
    }

    public void setChat(IChat chat){
        this.CurrentChat = chat;
        updateView(chat);
    }

    public IChat getChat(){
        return CurrentChat;
    }

    public void updateView(IChat chat){
        //clear chatArea of prev msgs
        chatArea.setText("");
        //clear msgField
        msgField.setText("");

        Iterator<IMessage> it = chat.iterator(0);
        IMessage msg;

        while(it.hasNext()){
            msg = it.next();
            index++;

            chatArea.append(msg.getSender() + ": " + msg.getContent() + "\n");
        }
    }
}
