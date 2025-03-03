package humusOn.orderManager.support.error.exception;

import humusOn.orderManager.support.error.ErrorCode;

public class OrderSendClientException extends BadRequestException {
    public OrderSendClientException() {
        super(ErrorCode.ORDER_SEND_CLIENT_ERROR, ErrorCode.ORDER_SEND_CLIENT_ERROR.getMessage());
    }

    public OrderSendClientException(String message) {
        super(ErrorCode.ORDER_SEND_CLIENT_ERROR, message != null ? message : ErrorCode.ORDER_SEND_CLIENT_ERROR.getMessage());
    }
}
