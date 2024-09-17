package domain.exceptions;

import core.BaseException;

public class NegativeVolumeException extends BaseException {
    public NegativeVolumeException(String message) {
        super(message, ExceptionCodes.NEGATIVE_VOLUME.name(), 400);
    }
}
