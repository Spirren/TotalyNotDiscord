package resources.model.interfaces;
import java.io.Serializable;

import resources.model.types.OperationType;

public interface NetworkObject extends Serializable {
    Object payload();
    OperationType operation();
}
