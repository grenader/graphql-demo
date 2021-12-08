package com.grenader.samples.graphql.springgraphql.model.employee;

import lombok.Data;

import java.math.BigDecimal;

/**
 * This class is used on GraphQL "mutator" only.
 */
@Data
public class SalaryInput {
    private Integer employeeId;
    private Long newSalary;
}
