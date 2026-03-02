//send message to database
//update UI
package client;

import client.appinterface.Interface;
import client.appinterface.view.ChatWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import resources.model.ImageMessage;
import resources.model.LoginRequest;
import resources.model.TextMessage;
import resources.model.dispatcher.DispatchRequest;
import resources.model.interfaces.IChat;
import resources.model.types.OperationType;
import resources.sockets.ChatClient;

public class Controller{ //implements "MessageListener"
    private final Interface ui;
    private final ChatWindow chatWindow;
    private final ChatClient client;

    public Controller(Interface ui, ChatClient client){
        this.ui = ui;
        chatWindow = ui.getChatWindow();
        this.client = client;

        //loadChats
        // loadChats();

        //sidebar listener
        ui.getChatList().addListSelectionListener(new ChatSelectionListener());

        //button listener
        chatWindow.getSendButton().addActionListener(new SendListener()); //(new SendListener(chatWindow.getIndex(), client))
        chatWindow.getLoginButton().addActionListener(new LoginListener());
        chatWindow.getFileChooser().addActionListener(new AttachListener());
    }

    class AttachListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "png"));

            int result = fileChooser.showOpenDialog(ui.getChatWindow());

            if(result == JFileChooser.APPROVE_OPTION){
                //String path = fileChooser.getSelectedFile().getAbsolutePath();
                File selectedFile = fileChooser.getSelectedFile();
                
                try {
                    //File file = fileChooser.getSelectedFile();
                    BufferedImage img = ImageIO.read(selectedFile);

                    if (img == null) {
                    // Show a popup to the user so they know why it failed
                        javax.swing.JOptionPane.showMessageDialog(ui.getChatWindow(), 
                        "The selected file is not a valid image or is unsupported.", 
                        "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return; 
                    }
    
                    // Create our new serializable Image object
                    ImageMessage imgMessage = new ImageMessage(LocalDateTime.now(), img, client.getUser(), -1, chatWindow.getChat().getChatId());
    
                    client.send(new DispatchRequest(imgMessage, OperationType.ADD));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //client.send(new DispatchRequest(imgMsg, OperationType.ADD));
            }
        }
    }

    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String uname = chatWindow.getLogin();
            LoginRequest login = new LoginRequest(uname, "password");//string, string
            client.send(new DispatchRequest(login, OperationType.LOGIN));
        }
    }

    class SendListener implements ActionListener{
        //private int index;
        ////private ChatClient client;
        //public SendListener(int index, ChatClient client){
        //    this.index=index;
        //    //this.client=client;
        //}
        
        @Override
        public void actionPerformed(ActionEvent e){
                String input = chatWindow.getMsg();
                TextMessage msg = new TextMessage(LocalDateTime.now(), input, client.getUser(), -1, chatWindow.getChat().getChatId());//-1 instead of ++index+8
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
