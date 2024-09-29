package usecases.createwaterflowsession;

import core.BaseException;
import core.BaseExceptionCodes;

public class WaterContainerNotFoundException extends BaseException {
    public WaterContainerNotFoundException() {
        super("Water container not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
