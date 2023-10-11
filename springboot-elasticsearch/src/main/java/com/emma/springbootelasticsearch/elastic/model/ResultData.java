package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
    private Long NumberFound;
    private Integer start;
    private Date qtime;
    private Object data;
    private String scrollId;
}
