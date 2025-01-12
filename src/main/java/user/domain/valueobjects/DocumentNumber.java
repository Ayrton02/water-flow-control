package user.domain.valueobjects;

import user.domain.exceptions.InvalidDocumentNumberException;

import java.util.regex.Pattern;

public class DocumentNumber {
    private static final String PATTERN = "\\b\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}\\b";
    private final String value;

    public DocumentNumber(String value) {
        if (!Pattern.matches(PATTERN, value)) {
            throw new InvalidDocumentNumberException("invalid document number!");
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format(
            "DocumentNumber[value=%s]",
            this.value
        );
    }
}
