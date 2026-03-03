package resources.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.IMessageVisitor;
import resources.model.interfaces.IUser;

public class ImageMessage implements IImageMessage {
    private LocalDateTime timeSent;
    private LocalDateTime lastEdited;
    private byte[] content;
    private IUser sender;
    private int index;
    private int chatID;

    public ImageMessage(LocalDateTime timeSent, LocalDateTime lastEdited, BufferedImage content, IUser sender, int index, int chatID) {
        if (content == null) throw new IllegalArgumentException();
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = imageToBytes(content, "png");
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;
    }

    public ImageMessage(LocalDateTime timeSent, BufferedImage content, IUser sender, int index, int chatID) {
        if (content == null) throw new IllegalArgumentException();
        this.timeSent = timeSent;
        this.content = imageToBytes(content, "png");
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;
    }

    private byte[] imageToBytes(BufferedImage image, String format) {
        try {
            if (image != null) {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                ImageIO.write(image, format, bout);
                return bout.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        try {
            ByteArrayInputStream bin = new ByteArrayInputStream(content);
            return ImageIO.read(bin);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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
}