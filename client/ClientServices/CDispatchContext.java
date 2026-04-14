package client.ClientServices;

import client.observer.MessageHandler;
import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.ISidebarUpdateListener;
import resources.model.interfaces.ILoginRequestGranted;
import resources.model.interfaces.IMessage;
import resources.model.types.OperationType;
import client.sockets.ChatClient;

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
        CLoginService loginService = new CLoginService(client, msgHandler, ui);

        dispatcher.register(ILoginRequestGranted.class, OperationType.LOGIN, loginService::login);
        dispatcher.register(ILoginRequestGranted.class, OperationType.ERROR, loginService::loginFail);

        CMessageService messageService = new CMessageService(msgHandler);

        dispatcher.register(IMessage.class, OperationType.ADD, messageService::addMessage);
    }
}
