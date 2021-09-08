package com.grenader.samples.graphql.springgraphql.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalaryInput {

    private String employeeId;
    private BigDecimal newSalary;
}
