package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RangeQuery extends QueryCommand {
    private List<RangeValue> rangeValues;
}
