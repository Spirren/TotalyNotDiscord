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
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IChatUpdateListener;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IMessageVisitor;
import resources.model.interfaces.ITextMessage;

//JTextPane
//StyledDocument - insertString(msgs), 
//updateview - renderMsg(doc, msg)
//renderMsg - print sender, if(image or text, instanceof?)

public class ChatWindow extends JPanel implements IChatUpdateListener{
    //private final JTextArea chatArea; //JTextPane
    private final JTextPane chatArea;
    private final JTextField msgField;
    private final JButton sendButton;
    private final JButton AttachButton; //filec
    private IChat CurrentChat;

    private final JPanel loginPanel;
    private final JButton loginButton;
    private final JTextField loginField;

    //private int index;

    //init ChatWindow
    public ChatWindow(){
        //LoginPanel
        loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Login");
        loginField = new JTextField(10);
        loginPanel.add(loginButton);
        loginPanel.add(loginField);

        //index=0;
        //Main Area
        setLayout(new BorderLayout());

        chatArea = new JTextPane();
        chatArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(chatArea); 

        JPanel bottomPanel = new JPanel(new BorderLayout());

        msgField = new JTextField();
        sendButton = new JButton("Send");
        AttachButton = new JButton("Upload pic");//filec

        bottomPanel.add(msgField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        bottomPanel.add(AttachButton, BorderLayout.WEST);

        add(loginPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public JButton getFileChooser(){//filec
        return AttachButton;
    }

    //public int getIndex(){
    //    return index;
    //}

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
        updateView();
    }

    public IChat getChat(){
        return CurrentChat;
    }

    @Override
    public void updateView(){
        SwingUtilities.invokeLater(() -> { //makes sure swing objs arent changed whilst we update the view
            if (CurrentChat == null) {
                chatArea.setText("Select a chat to start messaging");
                return;
            }

            //clear chatArea of prev msgs
            chatArea.setText("");
            //clear msgField
            msgField.setText("");

            StyledDocument doc = chatArea.getStyledDocument();
            Iterator<IMessage> it = CurrentChat.iterator();
            IMessage msg;

            while(it.hasNext()){
            msg = it.next();
            //msg.render(doc, msg);
            renderMessage(doc, msg);
            }

        });
    }

    public void renderMessage(StyledDocument doc, IMessage msg){
        msg.accept(new IMessageVisitor() {
            @Override
            public void visit(ITextMessage msg){
                try {
                    //doc.insertString(doc.getLength(), msg.getSender() + ": ", null);
                    doc.insertString(doc.getLength(), msg.getSender() + ": " + msg.getContent() + "\n", null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void visit(IImageMessage msg){
                try {
                    doc.insertString(doc.getLength(), msg.getSender() + ": ", null);
                    chatArea.setCaretPosition(doc.getLength()); // move cursor below image

                    ImageIcon icon = new ImageIcon(msg.getContent());
                    chatArea.insertIcon(scaleImage(icon, 200));

                    doc.insertString(doc.getLength(), "\n", null);
                } catch (BadLocationException e) {
                    e.printStackTrace();
                }
            }
            
        });
    }

    //for scaling images appropriately
    public ImageIcon scaleImage(ImageIcon icon, int maxWidth){
        Image img = icon.getImage();
        if (icon.getIconWidth() > maxWidth) {
            double ratio = (double) maxWidth / icon.getIconWidth();
            int newHeight = (int) (icon.getIconHeight() * ratio);
            img = img.getScaledInstance(maxWidth, newHeight, Image.SCALE_SMOOTH);
        }
        return new ImageIcon(img);
    }
}
