package resources.model.Services;

import resources.model.Chat;

public class ChatService {
    public void add(Chat c) { System.out.println("Add Chat " + c); }
    public void delete(Chat c) { System.out.println("Delete Chat " + c); }
    public void modify(Chat c) { System.out.println("Modify Chat " + c); }
}