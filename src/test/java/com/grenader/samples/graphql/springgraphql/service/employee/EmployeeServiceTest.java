package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.employee.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void testGetEmployeeById_Ok() {
        assertEquals(new Employee(1, "Andi"), service.getEmployeeById(1).get());
        assertEquals(new Employee(2, "Simona"), service.getEmployeeById(2).get());
    }

    @Test
    void testGetEmployeeById_error() {
        assertTrue(service.getEmployeeById(100).isEmpty());
    }
}