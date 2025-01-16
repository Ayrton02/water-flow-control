package waterflow.domain.exceptions;

import core.exception.BaseException;

public class WaterPumpInUseException extends BaseException {
  public WaterPumpInUseException(String message) {
    super(message, ExceptionCodes.WATER_PUMP_IN_USE.name());
  }
}
