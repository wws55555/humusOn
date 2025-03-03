package humusOn.orderManager.support.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_REQUEST("Invalid request"),
    METHOD_NOT_ALLOWED("Method not allowed"),

    NOT_FOUND_ORDER("Not found order"),
    DUPLICATED_ORDER("duplicated order");


    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }
}
