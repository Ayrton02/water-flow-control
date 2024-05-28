package domain.exceptions;

import core.BaseException;

public class NegativeCapacityException extends BaseException {
    public NegativeCapacityException(String message) {
        super(message, ExceptionCodes.NEGATIVE_CAPACITY.name(), 400);
    }
}
