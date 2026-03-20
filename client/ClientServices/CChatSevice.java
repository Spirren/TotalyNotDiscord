package client.ClientServices;

import resources.model.interfaces.IChat;

public class CChatSevice {
    public void add(IChat c) { System.out.println("Add Chat " + c); }
    public void delete(IChat c) { System.out.println("Delete Chat " + c); }
    public void modify(IChat c) { System.out.println("Modify Chat " + c); }
}
