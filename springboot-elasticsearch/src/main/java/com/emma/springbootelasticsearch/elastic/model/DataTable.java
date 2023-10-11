package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTable<T> {
    // current total records number
    private Long recordsFiltered;

    // page size
    private Integer length;

    // total records number
    private Long recordsTotal;

    // records data
    private List<T> data;
}
