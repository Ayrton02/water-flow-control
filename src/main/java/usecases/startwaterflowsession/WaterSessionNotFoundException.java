package usecases.startwaterflowsession;

import core.BaseException;
import core.BaseExceptionCodes;

public class WaterSessionNotFoundException extends BaseException {
    WaterSessionNotFoundException() {
        super("water session not found", BaseExceptionCodes.NOT_FOUND.name(), 404);
    }
}
