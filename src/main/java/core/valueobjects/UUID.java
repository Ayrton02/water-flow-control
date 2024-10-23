package core.valueobjects;

public class UUID extends ID {
    private UUID(String value) {
        super(value);
    }

    public static UUID generate() {
        String uuid = java.util.UUID.randomUUID().toString();
        return new UUID(uuid);
    }

    public static UUID from(String value) {
        return new UUID(value);
    }

    @Override
    public void validate(String value) {
        java.util.UUID.fromString(value);
    }
}
