package waterflow.domain.enums;

public enum VolumeType {
    LITER;
    public static VolumeType fromString(String any) {
      if (any.equalsIgnoreCase("LITER")) {
        return VolumeType.LITER;
      }
      return null;
    }
}
