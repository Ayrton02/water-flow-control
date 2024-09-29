package usecases.createwaterflowsession;

import core.BaseException;
import core.BaseExceptionCodes;

public class WaterPumpNotFoundException extends BaseException {
    public WaterPumpNotFoundException() {
        super("Water pump not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
