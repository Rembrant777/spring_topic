package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryCommand {
    // table name
    private String indexName;

    // key word
    private String keyWords;

    // search region
    private String search_field;

    // logic link operator
    private String operator;

    // sort order param
    private String sort;

    // start page
    private int start;

    // ret row numbers
    private int rows;

    // return fields
    private String return_field;

    private String startDate;

    // aggregation field name
    private String aggsField;

    // step
    private Integer step;

    // scroll id
    private String scrollid;

}
