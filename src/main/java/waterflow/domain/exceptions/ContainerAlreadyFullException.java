package waterflow.domain.exceptions;

import core.exception.BaseException;

public class ContainerAlreadyFullException extends BaseException {
    public ContainerAlreadyFullException(String message) {
        super(message, ExceptionCodes.CONTAINER_ALREADY_FULL.name(), 400);
    }
}
