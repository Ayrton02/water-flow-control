package waterflow.domain.exceptions;

import core.exception.BaseException;
import core.exception.BaseExceptionCodes;

public class WaterSessionNotFoundException extends BaseException {
    public WaterSessionNotFoundException() {
        super("water session not found", BaseExceptionCodes.NOT_FOUND.name(), 404);
    }
}
