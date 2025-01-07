package waterflow.domain.exceptions;

import core.exception.BaseException;

public class InvalidVolumeTypeException extends BaseException {
  public InvalidVolumeTypeException() {
    super("Invalid volume type", ExceptionCodes.INVALID_VOLUME_TYPE.name());
  }
}
