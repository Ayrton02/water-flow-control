package waterflow.domain.exceptions;

import core.exception.BaseException;

public class SafetyThresholdException extends BaseException {
    public SafetyThresholdException(String message) {
        super(message, ExceptionCodes.SAFETY_THRESHOLD.name(), 400);
    }
}
