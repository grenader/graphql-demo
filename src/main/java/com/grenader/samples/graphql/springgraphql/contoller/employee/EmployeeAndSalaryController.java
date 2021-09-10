package com.grenader.samples.graphql.springgraphql.contoller.employee;

import com.grenader.samples.graphql.springgraphql.model.Employee;
import com.grenader.samples.graphql.springgraphql.model.LinkFilter;
import com.grenader.samples.graphql.springgraphql.model.SalaryInput;
import com.grenader.samples.graphql.springgraphql.service.employee.EmployeeService;
import com.grenader.samples.graphql.springgraphql.service.employee.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeAndSalaryController {

    private final EmployeeService employeeService;

    private final SalaryService salaryService;

    @QueryMapping
    public List<Employee> employees() {
        return this.employeeService.getAllEmployees();
    }

    @QueryMapping
    public Mono<Employee> employee(@Argument int id) {
        return Mono.just(new Employee(id, "Fake Name"));
    }

    @QueryMapping
    public List<Employee> filterEmployees(@Argument String name, @Argument Float salary) {
        return Arrays.asList(new Employee(7, name));
    }

    @QueryMapping
    public List<Employee> filter2Employees(@Argument LinkFilter filter) {
        return Arrays.asList(new Employee(7, filter.getName()));
    }

    @QueryMapping
    public Mono<Employee> employeeByName(@Argument int id) {
        return Mono.just(new Employee(id, "Fake Name"));
    }

    @SchemaMapping
    public Mono<BigDecimal> salary(Employee employee) {
        return this.salaryService.getSalaryForEmployee(employee);
    }

    @MutationMapping
    public void updateSalary(@Argument("input") SalaryInput salaryInput) {
        String employeeId = salaryInput.getEmployeeId();
        BigDecimal salary = salaryInput.getNewSalary();
        this.salaryService.updateSalary(employeeId, salary);
    }

}
