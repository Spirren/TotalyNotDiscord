package resources.model.ServerServices;

import resources.model.LoginRequest;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ILoginRequest;
import resources.model.interfaces.ITextMessage;
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

        dispatcher.register(ITextMessage.class, OperationType.ADD, messageService::addMessage);
        dispatcher.register(ITextMessage.class, OperationType.DELETE, messageService::deleteMessage);
        dispatcher.register(ITextMessage.class, OperationType.MODIFY, messageService::modifyMessage);
        //
        dispatcher.register(IImageMessage.class, OperationType.ADD, messageService::addImage);
        dispatcher.register(IImageMessage.class, OperationType.DELETE, messageService::deleteImage);
        dispatcher.register(IImageMessage.class, OperationType.MODIFY, messageService::modifyImage);
    
        ChatService chatService = new ChatService();

        dispatcher.register(IChat.class, OperationType.ADD, chatService::add);
        dispatcher.register(IChat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(IChat.class, OperationType.MODIFY, chatService::modify);

        LoginService loginService = new LoginService(handler);

        dispatcher.register(ILoginRequest.class, OperationType.LOGIN, loginService::login);
        dispatcher.register(ILoginRequest.class, OperationType.ERROR, loginService::loginFail);
    }
}
