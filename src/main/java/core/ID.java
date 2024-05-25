package core;

public abstract class ID {
    protected String value;

    public abstract ID generate();

    public ID(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
