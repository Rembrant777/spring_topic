package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.http.HttpStats;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggsResultResponse {
    // response http status code
    private HttpStats status;

    // response code
    private Integer code;

    // error message
    private String message;

    // error stack message
    private String errStackMsg;

    HashMap<String, Object> rs;
}
