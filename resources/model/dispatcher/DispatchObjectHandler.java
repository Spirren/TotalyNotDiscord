package resources.model.dispatcher;

import resources.model.interfaces.ObjectHandler;

public class DispatchObjectHandler implements ObjectHandler {

    private final Dispatcher dispatcher;

    public DispatchObjectHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void handle(Object obj) {
        if (!(obj instanceof DispatchRequest request)) {
            throw new IllegalArgumentException(
                "Unsupported object received: " + obj.getClass()
            );
        }

        dispatcher.dispatch(
            request.getPayload(),
            request.getOperation()
        );
    }
}