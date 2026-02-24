package resources.model;

import java.time.LocalDateTime;

import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import java.awt.image.BufferedImage;

public class Image implements IMessage<BufferedImage> {
    private LocalDateTime timeSent;
    private LocalDateTime lastEdited;
    private BufferedImage content;
    private IUser sender;
    private int index;
    private int chatID;

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
    
}
