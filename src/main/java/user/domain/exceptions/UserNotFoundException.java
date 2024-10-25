package user.domain.exceptions;

import core.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found", ExceptionCodes.USER_NOT_FOUND.name(), 404);
    }
}
