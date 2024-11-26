package core.exception;

public abstract class BaseException extends RuntimeException {
    private final String code;
    private final String message;
    private final Integer httpCode;

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

    public Integer getHttpCode() {
        return httpCode;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
