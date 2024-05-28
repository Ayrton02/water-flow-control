package domain.valueobjects;

public abstract class ID {
    protected String value;

    public ID(String value) {
        this.validate(value);
        this.value = value;
    }

    public abstract void validate(String value);

    public String getValue() {
        return this.value;
    }
}
