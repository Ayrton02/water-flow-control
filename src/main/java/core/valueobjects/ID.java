package core.valueobjects;

import java.util.Objects;

public abstract class ID {
    protected String value;

    protected ID(String value) {
        this.validate(value);
        this.value = value;
    }

    public abstract void validate(String value);

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ID)) {
            return false;
        }

        return Objects.equals(((ID) obj).value, this.value);
    }

    @Override
    public String toString() {
        return String.format(
            "ID[value=%s]",
            value
        );
    }
}
