package com.paypal.bfs.test.employeeserv.impl;


import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final EmployeeResourceService employeeResourceService;

    public EmployeeResourceImpl(EmployeeResourceService employeeResourceService) {
        this.employeeResourceService = employeeResourceService;
    }

    @Override
    public ResponseEntity<Employee> submitEmployee(@RequestBody Employee newEmployee) {

        return new ResponseEntity<>(employeeResourceService.saveEmployee(newEmployee), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        Employee getEmployee = employeeResourceService.getEmployee(id);
        return new ResponseEntity<>(getEmployee, HttpStatus.OK);


    }


}
