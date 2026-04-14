package server.ServerServices;

import resources.model.dispatcher.Dispatcher;
import resources.model.interfaces.IImageMessage;
import resources.model.interfaces.ILoginRequest;
import resources.model.interfaces.ITextMessage;
import resources.model.types.OperationType;
import server.sockets.ClientHandler;

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
        MessageService messageService = new MessageService();

        dispatcher.register(ITextMessage.class, OperationType.ADD, messageService::addMessage);
        dispatcher.register(ITextMessage.class, OperationType.DELETE, messageService::deleteMessage);
        dispatcher.register(ITextMessage.class, OperationType.MODIFY, messageService::modifyMessage);
        //
        dispatcher.register(IImageMessage.class, OperationType.ADD, messageService::addImage);
        dispatcher.register(IImageMessage.class, OperationType.DELETE, messageService::deleteImage);
        dispatcher.register(IImageMessage.class, OperationType.MODIFY, messageService::modifyImage);

        LoginService loginService = new LoginService(handler);

        dispatcher.register(ILoginRequest.class, OperationType.LOGIN, loginService::login);
        dispatcher.register(ILoginRequest.class, OperationType.ERROR, loginService::loginFail);
    }
}
