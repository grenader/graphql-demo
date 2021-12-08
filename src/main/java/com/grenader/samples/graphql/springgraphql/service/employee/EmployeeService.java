package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeService {

	private List<Employee> staticEmployees = Arrays.asList(
			new Employee(1, "Andi"),
			new Employee(2, "Simona"),
			new Employee(3, "Marc")
	);

	public List<Employee> getAllEmployees() {
		return staticEmployees;
	}


	public Optional<Employee> getEmployeeById(Integer employeeId) {
		return staticEmployees.stream().
				filter(e -> { return  e.getId().equals(employeeId);}).
				findFirst();

	}
}
