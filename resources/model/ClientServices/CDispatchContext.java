package resources.model.ClientServices;

import resources.model.LoginRequest;
import resources.model.MessageHandler;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.IUser;
import resources.model.types.OperationType;
import resources.sockets.ChatClient;

public class CDispatchContext {
    
    private final Dispatcher dispatcher;
    private final ChatClient client;
    private MessageHandler msgHandler;

    public CDispatchContext(ChatClient client, MessageHandler msgHandler) {
        dispatcher = new Dispatcher();
        this.client = client;
        this.msgHandler = msgHandler;
        registerHandlers();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private void registerHandlers() {
        CUserService userService = new CUserService(client, msgHandler);

        dispatcher.register(IUser.class, OperationType.ADD, userService::add);
        dispatcher.register(IUser.class, OperationType.DELETE, userService::delete);
        dispatcher.register(IUser.class, OperationType.MODIFY, userService::modify);
        dispatcher.register(IUser.class, OperationType.LOGIN, userService::login);
        
        CMessageService messageService = new CMessageService(msgHandler);

        dispatcher.register(IMessage.class, OperationType.ADD, messageService::add);
        dispatcher.register(IMessage.class, OperationType.DELETE, messageService::delete);
        dispatcher.register(IMessage.class, OperationType.MODIFY, messageService::modify);
    
        CChatSevice chatService = new CChatSevice();

        dispatcher.register(IChat.class, OperationType.ADD, chatService::add);
        dispatcher.register(IChat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(IChat.class, OperationType.MODIFY, chatService::modify);

        CLoginService loginService = new CLoginService();

        dispatcher.register(LoginRequest.class, OperationType.LOGIN, loginService::login);
        dispatcher.register(LoginRequest.class, OperationType.ERROR, loginService::loginFail);
    }
}
