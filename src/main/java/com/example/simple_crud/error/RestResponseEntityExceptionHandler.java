package com.example.simple_crud.error;

import com.example.simple_crud.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFoundException(NotFoundException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorMessage> InternalServerErrorException(InternalServerErrorException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return  ResponseEntity.status((HttpStatus.INTERNAL_SERVER_ERROR)).body(message);
    }
}
