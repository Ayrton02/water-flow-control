package core;

public class BaseException extends RuntimeException {
    protected String code;
    protected String message;
    protected Integer httpCode = 500;

    public BaseException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public BaseException(String message, BaseExceptionCodes code) {
        super(message);
        this.message = message;
        this.code = code.toString();
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
