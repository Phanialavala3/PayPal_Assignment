package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeDuplicateEntryException;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotSavedException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeResourceService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    private EmployeeEntity employeeEntity;

    public EmployeeResourceService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;

    }

    public Employee getEmployee(String id) {
        Optional<EmployeeEntity> employeeEntity = Optional.ofNullable(employeeRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new EmployeeNotFoundException("Employee not Found")));
        Employee employee = modelMapper.map(employeeEntity.get(), Employee.class);
        return employee;
    }

    public Employee saveEmployee(Employee employee) {
        employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
        Optional<EmployeeEntity> oldEmployee = employeeRepository.findById(employee.getId());
        if (!oldEmployee.isPresent()) {
            employeeRepository.save(employeeEntity);
            Optional<EmployeeEntity> employeeEntity1 = Optional.ofNullable(employeeRepository.findById(employee.getId()).orElseThrow(() -> new EmployeeNotSavedException("Employee Not Saved")));
            return modelMapper.map(employeeEntity1.get(), Employee.class);

        } else {
            throw new EmployeeDuplicateEntryException("ID Already Exist");
        }

    }
}
