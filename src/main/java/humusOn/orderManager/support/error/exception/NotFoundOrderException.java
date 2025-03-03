package humusOn.orderManager.support.error.exception;

import humusOn.orderManager.support.error.ErrorCode;

public class NotFoundOrderException extends NotFoundException {
    public NotFoundOrderException() {
        super(ErrorCode.NOT_FOUND_ORDER, ErrorCode.NOT_FOUND_ORDER.getMessage());
    }

    public NotFoundOrderException(String message) {
        super(ErrorCode.NOT_FOUND_ORDER, message != null ? message : ErrorCode.NOT_FOUND_ORDER.getMessage());
    }
}
