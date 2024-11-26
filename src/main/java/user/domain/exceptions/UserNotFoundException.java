package user.domain.exceptions;

import core.exception.BaseException;
import core.exception.BaseExceptionCodes;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found", BaseExceptionCodes.NOT_FOUND.name(), 404);
    }
}
