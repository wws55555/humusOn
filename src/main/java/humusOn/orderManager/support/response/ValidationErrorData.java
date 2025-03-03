package humusOn.orderManager.support.response;

import humusOn.orderManager.support.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class ValidationErrorData {
    String code;
    String message;
    Map<String, List<String>> errors;

    public static ValidationErrorData of(ErrorCode errorCode, Map<String, List<String>> errors) {
        return new ValidationErrorData(errorCode.name(), errorCode.getMessage(), errors);
    }
}
