package org.example.dev17final.app.handler;

import org.example.dev17final.app.exception.UserAlreadyExistsException;
import org.example.dev17final.app.model.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorResponse handleException(UserAlreadyExistsException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.name(), exception.getMessage());
    }


    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Throwable exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name(), exception.getMessage());
    }
}
