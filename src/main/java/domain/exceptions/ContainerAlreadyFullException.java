package domain.exceptions;

import core.BaseException;

public class ContainerAlreadyFullException extends BaseException {
    public ContainerAlreadyFullException(String message) {
        super(message, ExceptionCodes.CONTAINER_ALREADY_FULL.name(), 400);
    }
}
