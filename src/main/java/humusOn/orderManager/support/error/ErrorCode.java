package humusOn.orderManager.support.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_REQUEST("Invalid request"),
    METHOD_NOT_ALLOWED("Method not allowed"),

    NOT_FOUND_ORDER("Not found order"),
    DUPLICATED_ORDER("duplicated order"),
    ORDER_SEND_CLIENT_ERROR("order send client error"),
    ORDER_SEND_SERVER_ERROR("order send server error");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
