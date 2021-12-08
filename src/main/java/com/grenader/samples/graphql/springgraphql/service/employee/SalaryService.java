package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.employee.Employee;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class SalaryService {

	public Mono<BigDecimal> getSalaryForEmployee(Employee employee) {

		switch(employee.getId()) {
			case 2:
				return Mono.just(new BigDecimal("31"));
			case 3:
				return Mono.just(new BigDecimal("42"));
			default:
				return Mono.just(new BigDecimal("25"));

		}


	}

	public void updateSalary(String employeeId, BigDecimal newSalary) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

}
