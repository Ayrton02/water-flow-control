package waterflow.domain.enums;

import waterflow.domain.exceptions.InvalidVolumeTypeException;

public enum VolumeType {
    LITER;
    public static VolumeType fromString(String any) {
      if (any.equalsIgnoreCase("LITER")) {
        return VolumeType.LITER;
      }
      throw new InvalidVolumeTypeException();
    }
}
