package humusOn.orderManager.support.error.exception;

import humusOn.orderManager.support.error.ErrorCode;

public abstract class ExternalServerException extends RuntimeException {

    private final ErrorCode errorCode;

    public ExternalServerException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ExternalServerException(ErrorCode errorCode, String message) {
        super(message != null ? message : errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
