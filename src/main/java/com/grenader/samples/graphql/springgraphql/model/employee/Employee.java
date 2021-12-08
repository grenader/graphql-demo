package com.grenader.samples.graphql.springgraphql.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class represent and object that can be queried via GraphQL
 *
 */

@Data
@AllArgsConstructor
public class Employee {

    private Integer id;

    private String name;

}