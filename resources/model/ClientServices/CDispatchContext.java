package resources.model.ClientServices;

import resources.model.MessageHandler;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IChat;
import resources.model.interfaces.IMessage;
import resources.model.interfaces.ISidebarUpdateListener;
import resources.model.interfaces.IUser;
import resources.model.interfaces.ILoginRequestGranted;
import resources.model.types.OperationType;
import resources.sockets.ChatClient;

public class CDispatchContext {
    
    private final Dispatcher dispatcher;
    private final ChatClient client;
    private MessageHandler msgHandler;
    private ISidebarUpdateListener ui;

    public CDispatchContext(ChatClient client, MessageHandler msgHandler, ISidebarUpdateListener ui, Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
        this.client = client;
        this.msgHandler = msgHandler;
        this.ui = ui;
        registerHandlers();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    private void registerHandlers() {
        CUserService userService = new CUserService();

        dispatcher.register(IUser.class, OperationType.ADD, userService::add);
        dispatcher.register(IUser.class, OperationType.DELETE, userService::delete);
        dispatcher.register(IUser.class, OperationType.MODIFY, userService::modify);
        dispatcher.register(IUser.class, OperationType.LOGIN, userService::login);
        
        CMessageService messageService = new CMessageService(msgHandler);

        dispatcher.register(IMessage.class, OperationType.ADD, messageService::addMessage);
        dispatcher.register(IMessage.class, OperationType.DELETE, messageService::deleteMessage);
        dispatcher.register(IMessage.class, OperationType.MODIFY, messageService::modifyMessage);
    
        CChatSevice chatService = new CChatSevice();

        dispatcher.register(IChat.class, OperationType.ADD, chatService::add);
        dispatcher.register(IChat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(IChat.class, OperationType.MODIFY, chatService::modify);

        CLoginService loginService = new CLoginService(client, msgHandler, ui);

        dispatcher.register(ILoginRequestGranted.class, OperationType.LOGIN, loginService::login);
        dispatcher.register(ILoginRequestGranted.class, OperationType.ERROR, loginService::loginFail);
    }
}
