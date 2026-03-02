package resources.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IMessageVisitor;
import resources.model.interfaces.IUser;

public class Image implements IMessage<BufferedImage>{
    private LocalDateTime timeSent;
    private LocalDateTime lastEdited;
    private BufferedImage content; //not transient
    private IUser sender;
    private int index;
    private int chatID;

    //want to convert img to bytes to be able to send
    private byte[] imageBytes;

    public Image(LocalDateTime timeSent, LocalDateTime lastEdited, BufferedImage content, IUser sender, int index, int chatID){
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = content;
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;

        //this.imageBytes = bufferedImageToBytes(content);
    }
    public Image(LocalDateTime timeSent, BufferedImage content, IUser sender, int index, int chatID){
        this.timeSent = timeSent;
        this.content = content;
        this.sender = sender;
        this.index = index;
        this.chatID = chatID;

        //this.imageBytes = bufferedImageToBytes(content);
    }

    //convert bufferedImg to png byte array
    //private byte[] bufferedImageToBytes(BufferedImage bi) {
    //    if (bi == null) return null;
    //    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
    //        ImageIO.write(bi, "png", baos);
    //        return baos.toByteArray();
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //        return null;
    //    }
    //}
//
    //@Override
    //public BufferedImage getContent() {
    //    //Reconstruct content
    //    if (content == null && imageBytes != null) {
    //        try (ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes)) {
    //            content = ImageIO.read(bais);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //    }
    //    return content;
    //}

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
    
}
