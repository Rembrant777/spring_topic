package com.emma.springbootelasticsearch.elastic.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Searching request
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticSearchRequest {
    // query condition
    private QueryCommand query;

    // filter condition
    private FilterCommand filter;
}
