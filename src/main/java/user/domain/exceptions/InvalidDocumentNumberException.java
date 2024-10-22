package user.domain.exceptions;

import core.exception.BaseException;

public class InvalidDocumentNumberException extends BaseException {
    public InvalidDocumentNumberException(String message) {
        super(message, ExceptionCodes.INVALID_DOCUMENT_NUMBER.name(), 400);
    }
}
