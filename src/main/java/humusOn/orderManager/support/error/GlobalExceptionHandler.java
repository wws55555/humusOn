package humusOn.orderManager.support.error;

import humusOn.orderManager.support.error.exception.BadRequestException;
import humusOn.orderManager.support.error.exception.NotFoundException;
import humusOn.orderManager.support.response.ErrorData;
import humusOn.orderManager.support.response.ValidationErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationErrorData> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, List<String>> errors = mapFieldErrors(e.getFieldErrors());
        log.error("handleMethodArgumentNotValidException: {}", e.getMessage(), e);

        ValidationErrorData errorData = ValidationErrorData.of(ErrorCode.INVALID_REQUEST, errors);
        return ResponseEntity.badRequest()
                .body(errorData);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ValidationErrorData> handleBindException(BindException e) {
        Map<String, List<String>> errors = mapFieldErrors(e.getFieldErrors());
        log.error("handleBindException: {}", e.getMessage() ,e);

        ValidationErrorData errorData = ValidationErrorData.of(ErrorCode.INVALID_REQUEST, errors);
        return ResponseEntity.badRequest()
                .body(errorData);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorData> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException: {}", e.getMessage(), e);

        ErrorData errorData = ErrorData.of(ErrorCode.INVALID_REQUEST, e.getMessage());
        return ResponseEntity.badRequest()
                .body(errorData);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorData> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException: {}", e.getMessage(), e);

        ErrorData errorData = ErrorData.of(ErrorCode.METHOD_NOT_ALLOWED, e.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorData);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorData> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: {}", e.getMessage(), e);

        ErrorData errorData = ErrorData.of(ErrorCode.INVALID_REQUEST, e.getMessage());
        return ResponseEntity.badRequest()
                .body(errorData);
    }

    public static Map<String, List<String>> mapFieldErrors(List<FieldError> fieldErrors) {
        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errors.computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                    .add(Optional.of(fieldError.getDefaultMessage()).orElse(""));
        }

        return errors;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorData> handleNotFoundException(NotFoundException e) {
        log.info("NotFoundException: {}, ErrorCode: {}", e.getMessage(), e.getErrorCode().name());

        ErrorData errorData = ErrorData.of(e.getErrorCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorData);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorData> handleBadRequestException(BadRequestException e) {
        log.info("BadRequestException: {}, ErrorCode: {}", e.getMessage(), e.getErrorCode().name());

        ErrorData errorData = ErrorData.of(e.getErrorCode(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorData);
    }

}

