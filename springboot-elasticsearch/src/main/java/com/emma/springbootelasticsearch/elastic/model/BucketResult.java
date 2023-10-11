package com.emma.springbootelasticsearch.elastic.model;

import lombok.Data;

@Data
public class BucketResult {
    private String visitTime;
    Long docNumber;
}
