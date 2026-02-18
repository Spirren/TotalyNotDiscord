public class ChatListener {
    Chat chat;

    public ChatListener(Chat c){
        chat = c;
    }

    @Override
    public update(Message m){
        chat.addMessage(m);
    }

}
