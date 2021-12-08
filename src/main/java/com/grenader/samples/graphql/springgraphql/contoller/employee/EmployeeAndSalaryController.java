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
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeAndSalaryController {

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
        return Mono.just(new Employee(id, "Fake Name"));
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
    public List<Employee> filterEmployees(@Argument String name, @Argument Float salary) {
        return Arrays.asList(new Employee(7, name));
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
    public List<Employee> filter2Employees(@Argument LinkFilter filter) {
        return Arrays.asList(new Employee(7, filter.getName()));
    }

    /**
     * todo: which one is used ?
     * @param id
     * @return
     */
    @QueryMapping
    public Mono<Employee> employeeById(@Argument int id) {
        return Mono.just(new Employee(id, "Fake Name"));
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
    public void updateSalary(@Argument("input") SalaryInput salaryInput) {
        String employeeId = salaryInput.getEmployeeId();
        BigDecimal salary = salaryInput.getNewSalary();
        this.salaryService.updateSalary(employeeId, salary);
    }

}
