package com.emma.springbootelasticsearch.elastic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeoDistance {
    private Double longitude;

    private Double latitude;

    private Double distance;

    private Integer pageNum;

    private Integer pageSize;
}
