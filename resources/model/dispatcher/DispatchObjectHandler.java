package resources.model.dispatcher;

import resources.model.interfaces.ObjectHandler;

public class DispatchObjectHandler implements ObjectHandler<DispatchRequest> {

    private final Dispatcher dispatcher;

    public DispatchObjectHandler(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void handle(DispatchRequest request) {
        dispatcher.dispatch(
            request.getPayload(),
            request.getOperation()
        );
    }
}