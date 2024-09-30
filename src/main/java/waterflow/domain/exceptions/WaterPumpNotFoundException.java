package waterflow.domain.exceptions;

import core.exception.BaseException;
import core.exception.BaseExceptionCodes;

public class WaterPumpNotFoundException extends BaseException {
    public WaterPumpNotFoundException() {
        super("Water pump not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
