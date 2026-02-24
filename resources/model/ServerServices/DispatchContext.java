package resources.model.ServerServices;

import resources.model.LoginRequest;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import resources.model.types.OperationType;
import resources.sockets.ClientHandler;

public class DispatchContext {

    private final Dispatcher dispatcher;
    private final ClientHandler handler;

    public DispatchContext(ClientHandler handler) {
        dispatcher = new Dispatcher();
        this.handler = handler;
        registerHandlers();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private void registerHandlers() {
        UserService userService = new UserService();

        dispatcher.register(IUser.class, OperationType.ADD, userService::add);
        dispatcher.register(IUser.class, OperationType.DELETE, userService::delete);
        dispatcher.register(IUser.class, OperationType.MODIFY, userService::modify);
        
        MessageService messageService = new MessageService();

        dispatcher.register(IMessage.class, OperationType.ADD, messageService::add);
        dispatcher.register(IMessage.class, OperationType.DELETE, messageService::delete);
        dispatcher.register(IMessage.class, OperationType.MODIFY, messageService::modify);
    
        ChatService chatService = new ChatService();

        dispatcher.register(IChat.class, OperationType.ADD, chatService::add);
        dispatcher.register(IChat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(IChat.class, OperationType.MODIFY, chatService::modify);

        LoginService loginService = new LoginService();

        dispatcher.register(LoginRequest.class, OperationType.LOGIN, payload -> {
            LoginRequest lr = (LoginRequest) payload;
            loginService.login(lr, handler);
        });
    }
}
