package resources.model;

import resources.model.interfaces.*;

public class ChatListener implements Subscriber{
    private final IChat chat;

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

    @Override
    public String toString(){
        return "ChatListener{" +
                "Chat=" + this.chat + "}";
    }

}
