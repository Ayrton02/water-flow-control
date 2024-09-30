package waterflow.domain.exceptions;

import core.exception.BaseException;
import core.exception.BaseExceptionCodes;

public class WaterContainerNotFoundException extends BaseException {
    public WaterContainerNotFoundException() {
        super("Water container not found", BaseExceptionCodes.NOT_FOUND, 404);
    }
}
