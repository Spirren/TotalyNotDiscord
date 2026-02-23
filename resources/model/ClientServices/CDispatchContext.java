package resources.model.ClientServices;

import resources.model.Chat;
import resources.model.LoginRequest;
import resources.model.Message;
import resources.model.User;
import resources.model.dispatcher.Dispatcher;
import resources.model.types.OperationType;

public class CDispatchContext {
    
    public final Dispatcher dispatcher;

    public CDispatchContext() {
        dispatcher = new Dispatcher();
        registerHandlers();
    }

    private void registerHandlers() {
        CUserService userService = new CUserService();

        dispatcher.register(User.class, OperationType.ADD, userService::add);
        dispatcher.register(User.class, OperationType.DELETE, userService::delete);
        dispatcher.register(User.class, OperationType.MODIFY, userService::modify);
        
        CMessageService messageService = new CMessageService();

        dispatcher.register(Message.class, OperationType.ADD, messageService::add);
        dispatcher.register(Message.class, OperationType.DELETE, messageService::delete);
        dispatcher.register(Message.class, OperationType.MODIFY, messageService::modify);
    
        CChatSevice chatService = new CChatSevice();

        dispatcher.register(Chat.class, OperationType.ADD, chatService::add);
        dispatcher.register(Chat.class, OperationType.DELETE, chatService::delete);
        dispatcher.register(Chat.class, OperationType.MODIFY, chatService::modify);

        CLoginService loginService = new CLoginService();

        dispatcher.register(LoginRequest.class, OperationType.LOGIN, loginService::login);
    }
}
