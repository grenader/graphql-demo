/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.grenader.samples.graphql.springgraphql.service.employee;

import com.grenader.samples.graphql.springgraphql.model.Employee;
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
		// empty
	}

}
