package resources.model.dispatcher;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import resources.model.types.OperationType;

public class Dispatcher {
    private final Map<Class<?>, Map<OperationType, Consumer<Object>>> handlers = new HashMap<>();

    public <T> void register(Class<T> clazz, OperationType operation, Consumer<T> handler) {
        handlers.computeIfAbsent(clazz, k -> new EnumMap<>(OperationType.class))
            .put(operation, obj -> handler.accept(clazz.cast(obj)));
    }

    public void dispatch(Object obj, OperationType operation) {
        Map<OperationType, Consumer<Object>> ops = handlers.get(obj.getClass());

        if (ops == null || !ops.containsKey(operation)) {
            throw new IllegalStateException("No handler for " + obj.getClass().getSimpleName() + " " + operation);
        }
        ops.get(operation).accept(obj);
    }
}
