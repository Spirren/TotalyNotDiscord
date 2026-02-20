package resources.model;

import resources.model.Services.*;
import resources.model.dispatcher.Dispatcher;
import resources.model.types.OperationType;

public class DispatchContext {

    public final Dispatcher dispatcher;

    public DispatchContext() {
        dispatcher = new Dispatcher();
        registerHandlers();
    }

    private void registerHandlers() {
        UserService userService = new UserService();

        dispatcher.register(User.class, OperationType.ADD, userService::add);
        dispatcher.register(User.class, OperationType.DELETE, userService::delete);
        dispatcher.register(User.class, OperationType.MODIFY, userService::modify);
        
        MessageService messageService = new MessageService();

        dispatcher.register(Message.class, OperationType.ADD, messageService::add);
        dispatcher.register(Message.class, OperationType.DELETE, messageService::delete);
        dispatcher.register(Message.class, OperationType.MODIFY, messageService::modify);
    
        ChatService chatService = new ChatService();

        dispatcher.register(Chat.class, OperationType.ADD, chatService::add);
        dispatcher.register(Chat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(Chat.class, OperationType.MODIFY, chatService::modify);
    }
}
