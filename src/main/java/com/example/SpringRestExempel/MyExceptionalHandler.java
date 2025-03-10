package com.example.SpringRestExempel;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyExceptionalHandler {

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException exception) {
        Map<String, String> errorlist = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(e -> errorlist.put(e.getField(), e.getDefaultMessage()));

        return errorlist;
    }

}
