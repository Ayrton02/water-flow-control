package core.exception;

public class BaseException extends RuntimeException {
    protected final String code;
    protected final String message;
    protected final Integer httpCode;

    public BaseException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
        this.httpCode = 500;
    }

    public BaseException(String message, BaseExceptionCodes code) {
        super(message);
        this.message = message;
        this.code = code.toString();
        this.httpCode = 500;
    }

    public BaseException(String message, BaseExceptionCodes code,  Integer httpCode) {
        super(message);
        this.message = message;
        this.code = code.toString();
        this.httpCode = httpCode;
    }

    public BaseException(String message, String code, Integer httpCode) {
        super(message);
        this.message = message;
        this.code = code;
        this.httpCode = httpCode;
    }
}
