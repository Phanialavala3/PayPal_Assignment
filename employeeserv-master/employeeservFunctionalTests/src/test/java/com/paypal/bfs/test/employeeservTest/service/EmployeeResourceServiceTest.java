package com.paypal.bfs.test.employeeservTest.service;

import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeDuplicateEntryException;
import com.paypal.bfs.test.employeeserv.Exceptions.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class EmployeeResourceServiceTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    @Mock
    private EmployeeRepository employeeRepository;
    @MockBean
    private EmployeeResourceService service;

    EmplyeeTestUtil emplyeeTestUtil = new EmplyeeTestUtil();

    @BeforeEach
    public void setup() {
        service = new EmployeeResourceService(employeeRepository, new ModelMapper());
    }

    @Test
    public void saveEmployee_should_save_employee_to_db_success() {
        //given
        Employee employee = emplyeeTestUtil.buildEmployee();
        Employee employeeEntityMap = new ModelMapper().map(emplyeeTestUtil.buildEmployeeEntity(), Employee.class);

        //when
        when(service.saveEmployee(employee)).thenReturn(employeeEntityMap);
        Employee savedEmployee = service.saveEmployee(employee);

        //then
        assertEquals(employeeEntityMap, savedEmployee);
    }


    @Test
    public void getEmployee_should_return_employee_with_given_id_success() {
        //given
        Employee employee = emplyeeTestUtil.buildEmployee();
        service.saveEmployee(employee);
        Employee employeeEntityMap = new ModelMapper().map(emplyeeTestUtil.buildEmployeeEntity(), Employee.class);

        //when
        when(service.getEmployee("1")).thenReturn(employeeEntityMap);

        //then
        assertEquals(employeeEntityMap, service.getEmployee("1"));
    }


    @Test
    public void getEmployee_should_return_employee_with_given_id_failure() {
        //given
        String message = "Employee not Found";

        //when
        when(service.getEmployee("1")).thenThrow(new EmployeeNotFoundException("Employee not Found"));

        //then
        expectedEx.expect(EmployeeNotFoundException.class);
        expectedEx.expectMessage(message);
        given(service.getEmployee("1")).willThrow(new EmployeeNotFoundException(message));
    }


    @Test
    public void saveEmployee_should_not_save_duplicate_employee_to_db_failure() {
        //given
        Employee employee = emplyeeTestUtil.buildEmployee();
        service.saveEmployee(employee);
        String message = "ID Already Exist";
        //when
        when(service.saveEmployee(employee)).thenThrow(new EmployeeDuplicateEntryException("ID Already Exist"));

        //then
        expectedEx.expect(EmployeeDuplicateEntryException.class);

        expectedEx.expectMessage(message);
        given(service.saveEmployee(employee)).willThrow(new EmployeeDuplicateEntryException(message));

    }


    @Test(expected = EmployeeNotFoundException.class)
    public void getEmployee_should_return_NotFoundException_when_id_not_found_from_db_failure() {
        //when
        when(service.getEmployee("2")).thenThrow(EmployeeNotFoundException.class);
        service.getEmployee("2");
        //then
        expectedEx.expect(EmployeeNotFoundException.class);
        expectedEx.expectMessage("Employee not Found");
    }


}
