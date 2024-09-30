package waterflow.domain.exceptions;

import core.exception.BaseException;
import core.exception.BaseExceptionCodes;

public class WaterSourceNotFoundException extends BaseException {
    public WaterSourceNotFoundException() {
        super("Water source not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
