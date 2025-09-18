package br.com.udemy.tasks.exception;

import br.com.udemy.tasks.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Objects;

@ControllerAdvice
public class CustomException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return Mono.just(ex)
                .map(ErrorResponse::internalError)
                .map(error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error))
                .block();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        return Mono.just(Objects.requireNonNull(ex.getBindingResult().getFieldError()))
                .map(ErrorResponse::invalidArgumentsError)
                .map(error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error))
                .block();
    }

}
