package resources.model.interfaces;

import resources.model.Command;

public interface CommandHandler {
    void handleCommand(Command command);
}
