package com.grenader.samples.graphql.springgraphql.model.employee;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class is used to represent a filter in GraphQL queries
 */
@Data
@AllArgsConstructor
public class LinkFilter {
    private String name;
    private Float salary;
}