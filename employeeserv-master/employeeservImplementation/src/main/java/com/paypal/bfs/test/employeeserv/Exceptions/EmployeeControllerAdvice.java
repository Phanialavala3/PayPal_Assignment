package com.paypal.bfs.test.employeeserv.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String notFound(EmployeeNotFoundException e) {
        return e.getMessage();

    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmployeeNotSavedException.class)
    public String notSaved(EmployeeNotSavedException e) {
        return e.getMessage();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeDuplicateEntryException.class)
    public String duplicate(EmployeeDuplicateEntryException e) {
        return e.getMessage();

    }

}
