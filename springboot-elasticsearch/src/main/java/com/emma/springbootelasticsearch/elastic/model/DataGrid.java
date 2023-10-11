package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGrid<T> {
    // current page number
    private Integer current;

    // lines cnt in each page
    private Integer rowCount;

    // total lines
    private Long total;

    // rows
    private List<T> rows;
}
