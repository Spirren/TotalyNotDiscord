package resources.model;

import java.time.LocalDateTime;
import resources.model.interfaces.IUser;
import resources.model.interfaces.IMessage;

public class Message implements IMessage {
    private LocalDateTime timeSent;
    private LocalDateTime lastEdited;
    private String content;
    private IUser sender;
    private int index;

    public Message(LocalDateTime timeSent, LocalDateTime lastEdited, String content, IUser sender, int index) {
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = content;
        this.sender = sender;
        this.index = index;
    }

    public Message(LocalDateTime timeSent, String content, IUser sender, int index) {
        this.timeSent = timeSent;
        this.content = content;
        this.sender = sender;
        this.index = index;
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
}