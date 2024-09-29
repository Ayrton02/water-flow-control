package domain.exceptions;

import core.BaseException;

public class WaterFlowSessionException extends BaseException {
    public WaterFlowSessionException(String message) {
        super(message, ExceptionCodes.WATER_FLOW_SESSION.name(), 400);
    }
}
