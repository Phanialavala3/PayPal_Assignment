package com.paypal.bfs.test.employeeserv.Exceptions;


public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

}
