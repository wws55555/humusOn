package humusOn.orderManager.support.error.exception;

import humusOn.orderManager.support.error.ErrorCode;

public class DuplicateOrderException extends BadRequestException {
    public DuplicateOrderException() {
        super(ErrorCode.DUPLICATED_ORDER, ErrorCode.DUPLICATED_ORDER.getMessage());
    }

    public DuplicateOrderException(String message) {
        super(ErrorCode.DUPLICATED_ORDER, message != null ? message : ErrorCode.DUPLICATED_ORDER.getMessage());
    }
}
