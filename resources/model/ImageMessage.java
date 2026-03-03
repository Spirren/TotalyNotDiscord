package resources.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.IMessageVisitor;
import resources.model.interfaces.IUser;

public class ImageMessage implements IImageMessage {
    private final LocalDateTime timeSent;
    private final LocalDateTime lastEdited;
    private final BufferedImage content;
    private final IUser sender;
    private int index;
    private final int chatID;

    public ImageMessage(LocalDateTime timeSent, LocalDateTime lastEdited, BufferedImage content, IUser sender,
            int index, int chatID) {
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = content;
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;
    }

    public ImageMessage(LocalDateTime timeSent, BufferedImage content, IUser sender, int index, int chatID) {
        this.timeSent = timeSent;
        this.content = content;
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;
    }

    @Override
    public LocalDateTime getTime() {
        return timeSent;
    }

    @Override
    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    @Override
    public IUser getSender() {
        return sender;
    }

    @Override
    public BufferedImage getContent() {
        return content;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public int getChatID() {
        return chatID;
    }

    @Override
    public void accept(IMessageVisitor visitor){
        visitor.visit(this);
    }

    //public void render(ChatWindow chatArea){
    //    chatArea.TextRender();
    //}
    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public String toString(){
        return "ImageMessage{" +
                "chatID=" + this.chatID + ", " +
                "index=" + this.index + ", " +
                "sender=" + this.sender + ", " +
                "content=" + this.content + ", " +
                "lastEdited=" + this.lastEdited + ", " +
                "timeSent=" + this.timeSent + ", " +
                "}";
    }

    @Override
    public boolean equals(Object o){
        if(this == o){return true;}
        if(o == null){return false;}
        if(this.getClass() != o.getClass()){return false;}
        return this.timeSent.equals(((ImageMessage)o).getTime()) &&
               this.lastEdited.equals(((ImageMessage)o).getLastEdited()) &&
               this.content.equals(((ImageMessage)o).getContent()) &&
               this.sender.equals(((ImageMessage)o).getSender()) &&
               this.index == ((ImageMessage)o).getIndex() &&
               this.chatID == ((ImageMessage)o).getChatID();
    }
}