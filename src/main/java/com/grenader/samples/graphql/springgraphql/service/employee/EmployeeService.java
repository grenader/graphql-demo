package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.Employee;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeService {

	public List<Employee> getAllEmployees() {
		return Arrays.asList(
				new Employee(1, "Andi"),
				new Employee(2, "Simona"),
				new Employee(3, "Marc")
		);
	}

}
