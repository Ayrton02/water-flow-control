package usecases.createwaterflowsession;

import core.BaseException;
import core.BaseExceptionCodes;

public class WaterSourceNotFoundException extends BaseException {
    public WaterSourceNotFoundException() {
        super("Water source not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
