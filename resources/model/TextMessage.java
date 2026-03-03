package resources.model;

import java.time.LocalDateTime;
import resources.model.interfaces.IMessageVisitor;
import resources.model.interfaces.ITextMessage;
import resources.model.interfaces.IUser;

public class TextMessage implements ITextMessage {

    private final LocalDateTime timeSent;
    private final LocalDateTime lastEdited;
    private final String content;
    private final IUser sender;
    private int index;
    private final int chatID;

    public TextMessage(LocalDateTime timeSent, LocalDateTime lastEdited, String content, IUser sender, int index,
            int chatID) {
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = content;
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;
    }

    public TextMessage(LocalDateTime timeSent, String content, IUser sender, int index, int chatID) {
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
    public String getContent() {
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
    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString(){
        return "TextMessage{" +
                "chatID=" + this.chatID + ", " +
                "index=" + this.index + ", " +
                "sender=" + this.sender + ", " +
                "content=" + this.content + ", " +
                "lastEdited=" + this.lastEdited + ", " +
                "timeSent=" + this.timeSent + ", " +
                "}";
    }
}
