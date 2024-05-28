package core;

public class BaseException extends Exception {
    protected String code;
    protected String message;
    protected Integer httpCode = 500;

    public BaseException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseException(String message, String code, Integer httpCode) {
        super(message);
        this.message = message;
        this.code = code;
        this.httpCode = httpCode;
    }
}
