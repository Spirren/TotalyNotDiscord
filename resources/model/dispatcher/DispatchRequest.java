package resources.model.dispatcher;

import java.io.Serializable;

import resources.model.types.OperationType;

public class DispatchRequest implements Serializable {
    private final Object payload;
    private final OperationType operation;

    public DispatchRequest(Object payload, OperationType operation) {
        this.payload = payload;
        this.operation = operation;
    }

    public Object getPayload() {
        return payload;
    }

    public OperationType getOperation() {
        return operation;
    }
}
