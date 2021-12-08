package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SalaryService {

	@Autowired
	EmployeeService employeeService;
	private Map<Integer, BigDecimal> employeeSalaryGrid = new LinkedHashMap<>();

	public Mono<BigDecimal> getSalaryForEmployee(Employee employee) {

		if (employeeSalaryGrid.get(employee.getId()) != null)
			return Mono.just(employeeSalaryGrid.get(employee.getId()));

		switch(employee.getId()) {
			case 2:
				return Mono.just(new BigDecimal("31"));
			case 3:
				return Mono.just(new BigDecimal("42"));
			default:
				return Mono.just(new BigDecimal("25"));

		}


	}

	public Employee updateSalary(Integer employeeId, BigDecimal newSalary) {
		Employee employee = employeeService.getEmployeeById(employeeId);

		// store for future calls.
		employeeSalaryGrid.put(employeeId, newSalary);

		return employee;
	}

}
