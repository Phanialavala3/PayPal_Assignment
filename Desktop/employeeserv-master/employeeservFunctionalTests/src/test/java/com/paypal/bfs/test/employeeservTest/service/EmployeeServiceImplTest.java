package com.paypal.bfs.test.employeeservTest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import com.paypal.bfs.test.employeeserv.service.EmployeeResourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @MockBean
    private MockMvc mockMvc;

    @InjectMocks
    EmployeeResourceImpl employeeResourceImpl;

    @Mock
    private EmployeeResourceService service;

    ObjectMapper om = new ObjectMapper();


    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(employeeResourceImpl).build();
    }

    @Test
    public void saveEmployee_should_save_employee_to_db_and_return_saved_employee() throws Exception {
        //given

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("First");
        employee.setLastName("Last");
        employee.setDateOfBirth(new Date());
        employee.setAddress("Rogers");

        String jsonRequest = om.writeValueAsString(employee);

        //when
        when(employeeResourceImpl.submitEmployee(employee).getBody()).thenReturn(employee);

        String url = "/v1/bfs/employees/";

        mockMvc.perform(MockMvcRequestBuilders
                .post(url).content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        Employee expected = employeeResourceImpl.submitEmployee(employee).getBody();
        //when
        assertEquals(expected, employee);

    }

    @Test
    public void getEmployee_should_return_employee_from_db_which_had_given_id_success() throws Exception {
        //given

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("First");
        employee.setLastName("Last");
        employee.setDateOfBirth(new Date());
        employee.setAddress("Rogers");

        employeeResourceImpl.submitEmployee(employee);

        //when
        when(employeeResourceImpl.employeeGetById("1").getBody()).thenReturn(employee);

        String url = "/v1/bfs/employees/1";

        mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

        Employee expected = employeeResourceImpl.employeeGetById("1").getBody();
        //when
        assertEquals(expected, employee);

    }

}
