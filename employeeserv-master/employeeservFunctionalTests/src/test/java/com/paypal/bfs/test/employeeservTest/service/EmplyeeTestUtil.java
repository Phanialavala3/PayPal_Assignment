package com.paypal.bfs.test.employeeservTest.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;

import java.util.Calendar;
import java.util.Date;

public class EmplyeeTestUtil {
    public Employee buildEmployee() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("First Name");
        employee.setLastName("Last Name");
        Date dbo = new Date(1991, 04, 06);
        System.out.println("Date :" + dbo);
        employee.setDateOfBirth(dbo);
        employee.setAddress("Rogers");
        return employee;
    }

    public EmployeeEntity buildEmployeeEntity() {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1);
        employeeEntity.setFirstName("First Name");
        employeeEntity.setLastName("Last Name");
        Date dbo = new Date(1991, Calendar.APRIL, 28);
        employeeEntity.setDateOfBirth(dbo);
        employeeEntity.setAddress("Rogers");
        return employeeEntity;
    }
}
