package core.valueobjects;

public class UUID extends ID {
    public UUID(String value) {
        super(value);
    }

    public static UUID generate() {
        String uuid = java.util.UUID.randomUUID().toString();
        return new UUID(uuid);
    }

    @Override
    public void validate(String value) {
        java.util.UUID.fromString(value);
    }
}
