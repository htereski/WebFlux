package br.com.udemy.tasks.model;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ErrorResponse {
    private int status;

    private String message;

    public ErrorResponse() {}

    public ErrorResponse(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public static ErrorResponse internalError(RuntimeException ex) {
        return ErrorResponse.builder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withMessage(ex.getMessage())
                .build();
    }

    public static ErrorResponse invalidArgumentsError(FieldError fieldError) {
        return ErrorResponse.builder()
                .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .withMessage(fieldError.getDefaultMessage())
                .build();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builderFrom(ErrorResponse response) {
        return new Builder(response);
    }

    public static class Builder {
        private int status;

        private String message;

        public Builder() {}

        public Builder(ErrorResponse response) {
            this.status = response.status;
            this.message = response.message;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(this);
        }

    }
}
