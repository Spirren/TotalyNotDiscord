import java.time.LocalDateTime;

public class Message{
    private LocalDateTime timeSent;
    private LocalDateTime lastEdited;
    private String content;
    private IUser sender;
    private int index;

    public Message(LocalDateTime timeSent, LocalDateTime lastEdited, String content, IUser sender, int index){
        this.timeSent = timeSent;
        this.lastEdited = lastEdited;
        this.content = content;
        this.sender = sender;
        this.index = index;
    }

    public Message(LocalDateTime timeSent, String content, IUser sender, int index){
        this.timeSent = timeSent;
        this.content = content;
        this.sender = sender;
        this.index = index;
    }

    @override
    public LocalDateTime getTime(){
        return timeSent;
    }

    @override
    public LocalDateTime getLastEdited(){
        return lastEdited;
    }

    @override
    public IUser getSender(){
        return sender;
    }

    @override
    public String getContent(){
        return content;
    }

    @override
    public int getIndex(){
        return index;
    } 
}