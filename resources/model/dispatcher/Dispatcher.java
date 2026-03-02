package resources.model.dispatcher;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import resources.model.interfaces.ObjectHandler;
import resources.model.types.OperationType;

public class Dispatcher {

    private final Map<Class<?>, Map<OperationType, ObjectHandler<?>>> handlers = new HashMap<>();

    public <T> void register(Class<T> type, OperationType operation, ObjectHandler<? super T> handler) {

        handlers.computeIfAbsent(type, k -> new EnumMap<>(OperationType.class)).put(operation, handler);
    }

    public <T> void dispatch(T obj, OperationType operation) {

        Class<?> payloadClass = obj.getClass();
        ObjectHandler<T> handler = findHandler(payloadClass, operation);
        if (handler == null) {
            throw new IllegalStateException("No handler for " + payloadClass.getSimpleName() + " " + operation);
        }

        handler.handle(obj);
    }

    @SuppressWarnings("unchecked")
    private <T> ObjectHandler<T> findHandler(Class<?> payloadClass, OperationType operation) {

        Map<OperationType, ObjectHandler<?>> exact = handlers.get(payloadClass);
        if (exact != null && exact.containsKey(operation)) {
            return (ObjectHandler<T>) exact.get(operation);
        }

        for (Map.Entry<Class<?>, Map<OperationType, ObjectHandler<?>>> entry : handlers.entrySet()) {

            if (entry.getKey().isAssignableFrom(payloadClass)) {

                ObjectHandler<?> candidate = entry.getValue().get(operation);

                if (candidate != null) {
                    return (ObjectHandler<T>) candidate;
                }
            }
        }

        return null;
    }
}
