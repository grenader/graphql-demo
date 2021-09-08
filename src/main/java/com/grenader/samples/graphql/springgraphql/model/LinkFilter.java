package com.grenader.samples.graphql.springgraphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkFilter {
    private String name;
    private Float salary;
}