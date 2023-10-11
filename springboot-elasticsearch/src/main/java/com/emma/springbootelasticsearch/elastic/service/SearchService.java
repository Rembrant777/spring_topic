package com.emma.springbootelasticsearch.elastic.service;

import com.emma.springbootelasticsearch.elastic.model.ElasticSearchRequest;
import org.elasticsearch.action.search.SearchResponse;

public interface SearchService {
    /**
     * multi fields && regular page-nation
     * @param request
     * @return
     */
    SearchResponse query_string(ElasticSearchRequest request);
}
