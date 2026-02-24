package resources.model.dispatcher;

import java.io.Serializable;

import resources.model.LoginRequest;
import resources.model.types.OperationType;

public class DispatchRequest implements Serializable {
    private final Object payload;
    private final OperationType operation;

    /** Creates a network request
     * 
     * @param payload Any object
     * @param operation OperationType to be executed
     */
    public DispatchRequest(Object payload, OperationType operation) {
        if (payload == null) {
            throw new IllegalArgumentException("Payload cannot be null");
        }
        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        
        this.payload = payload;
        this.operation = operation;
    }

    /** Used for Login operations
     * 
     * @pre {@CODE OperationType} has to be LOGIN
     * 
     * @param username
     * @param password
     * @param operation LOGIN is only supported operation
     */
    public DispatchRequest(String username, String password, OperationType operation) {
        if (operation != OperationType.LOGIN) {
            throw new IllegalArgumentException("This constructor is only for LOGIN operations");
        }
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        this.payload = new LoginRequest(username, password);
        this.operation = operation;
    }

    public Object getPayload() {
        return payload;
    }

    public OperationType getOperation() {
        return operation;
    }
}
