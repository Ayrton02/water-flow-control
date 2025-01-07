package waterflow.domain.exceptions;

import core.exception.BaseException;

public class InvalidTimeMeasurementUnitException extends BaseException {
  public InvalidTimeMeasurementUnitException() {
    super("Invalid time measurement unit", ExceptionCodes.INVALID_TIME_MEASUREMENT_UNIT.name());
  }
}
