package humusOn.orderManager.support.error.exception;

import humusOn.orderManager.support.error.ErrorCode;

public class OrderSendServerException extends ExternalServerException {
    public OrderSendServerException() {
        super(ErrorCode.ORDER_SEND_SERVER_ERROR, ErrorCode.ORDER_SEND_SERVER_ERROR.getMessage());
    }

    public OrderSendServerException(String message) {
        super(ErrorCode.ORDER_SEND_SERVER_ERROR, message != null ? message : ErrorCode.ORDER_SEND_SERVER_ERROR.getMessage());
    }
}
