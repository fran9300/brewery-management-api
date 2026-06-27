package com.brewery.api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(
            ResourceNotFoundException ex,
            WebRequest request){

        return new ErrorResponse(
                LocalDateTime.now(),
                404,
                "Not Found",
                ex.getMessage(),
                request.getDescription(false)
        );
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(
            MethodArgumentNotValidException ex,
            WebRequest request){


        String message =
                ex.getBindingResult()
                        .getFieldErrors()
                        .getFirst()
                        .getDefaultMessage();


        return new ErrorResponse(
                LocalDateTime.now(),
                400,
                "Bad Request",
                message,
                request.getDescription(false)
        );
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDatabase(WebRequest request, DataIntegrityViolationException ex){

        String message = "Database constraint violation";

        if(ex.getMessage().contains("fk_ingredient")){
            message = "Ingredient cannot be deleted because it is used in a recipe";
        }

        return new ErrorResponse(
                LocalDateTime.now(),
                409,
                "Conflict",
                message,
                request.getDescription(false)

        );
    }
}
