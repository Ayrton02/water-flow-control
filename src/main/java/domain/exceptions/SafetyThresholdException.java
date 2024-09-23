package domain.exceptions;

import core.BaseException;

public class SafetyThresholdException extends BaseException {
    public SafetyThresholdException(String message) {
        super(message, ExceptionCodes.SAFETY_THRESHOLD.name(), 400);
    }
}
