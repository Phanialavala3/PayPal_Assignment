package com.paypal.bfs.test.employeeserv.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class EmployeeEntity {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;

}
