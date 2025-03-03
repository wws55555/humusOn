package humusOn.orderManager.support.response;

import humusOn.orderManager.support.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorData {
    String code;
    String message;

    public static ErrorData from(ErrorCode errorCode) {
        return new ErrorData(errorCode.name(), errorCode.getMessage());
    }

    public static ErrorData of(ErrorCode errorCode, String errorMessage) {
        return new ErrorData(errorCode.name(), errorMessage);
    }
}
