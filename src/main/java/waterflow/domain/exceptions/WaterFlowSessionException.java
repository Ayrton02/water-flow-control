package waterflow.domain.exceptions;

import core.exception.BaseException;

public class WaterFlowSessionException extends BaseException {
    public WaterFlowSessionException(String message) {
        super(message, ExceptionCodes.WATER_FLOW_SESSION.name(), 400);
    }
}
