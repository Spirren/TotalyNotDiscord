import resources.model.interfaces.*;

public class ChatListener implements Subscriber{
    private IChat chat;

    public ChatListener(IChat c){
        chat = c;
    }

    @Override
    public void update(IMessage m){
        this.chat.addMessage(m);
    }

    @Override
    public Integer getSubKey(){
        return this.chat.getChatId();
    }

}
