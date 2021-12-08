package com.grenader.samples.graphql.springgraphql.contoller.employee;

import com.grenader.samples.graphql.springgraphql.model.employee.Employee;
import com.grenader.samples.graphql.springgraphql.model.employee.LinkFilter;
import com.grenader.samples.graphql.springgraphql.model.employee.SalaryInput;
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
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    // Define service called in this controller
    private final EmployeeService employeeService;
    private final SalaryService salaryService;

    /**
     * Get all Employees
     * @return
     */
    @QueryMapping
    public List<Employee> employees() {
        return this.employeeService.getAllEmployees();
    }

    /**
     * Get Employee by Id - filters one employee
     * @param id
     * @return
     */
    @QueryMapping
    public Mono<Employee> employee(@Argument int id) {

        Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);

        return Mono.just(optionalEmployee.orElseGet(
                () -> new Employee(id, "Fake Name"))
        );
    }

    /**
     * Search employees by name and salary - filters by name and salary
     * Both are required.
     *
     * @param name
     * @param salary
     * @return
     */
    @QueryMapping
    public Employee findEmployee(@Argument String name, @Argument Float salary) {
        return new Employee(7, name);
    }

    /**
     * Filters employees by name and salary - filters by name and salary.
     * //todo: check that
     * What is the difference with the previous one ?
     * ?? both are required.
     *
     * @param filter
     * @return
     */
    @QueryMapping
    public Employee findEmployeeFilter(@Argument LinkFilter filter) {
        return new Employee(7, filter.getName());
    }

    /**
     * This method links Employee objects "salary" field to a service call.
     * @param employee
     * @return
     */
    @SchemaMapping
    public Mono<BigDecimal> salary(Employee employee) {
        return this.salaryService.getSalaryForEmployee(employee);
    }

    /**
     * GraphQL mutation (data update), this method represent one mutation query.
     * @param salaryInput
     */
    @MutationMapping
    public Employee updateSalary(@Argument("input") SalaryInput salaryInput) {
        Integer employeeId = salaryInput.getEmployeeId();
        Long salary = salaryInput.getNewSalary();

        return salaryService.updateSalary(employeeId, BigDecimal.valueOf(salary));
    }

}
