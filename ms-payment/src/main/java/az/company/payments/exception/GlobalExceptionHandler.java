package az.company.payments.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static az.company.payments.model.enums.ErrorMessage.SERVER_ERROR;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception e) {
        return ErrorResponse.builder()
                .message(SERVER_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public List<ErrorResponse> handle(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> ErrorResponse.builder()
                        .message(error.getDefaultMessage()
                        ).build()).toList();
    }

}
