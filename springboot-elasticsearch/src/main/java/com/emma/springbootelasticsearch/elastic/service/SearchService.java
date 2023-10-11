package com.emma.springbootelasticsearch.elastic.service;

import com.emma.springbootelasticsearch.elastic.model.ElasticSearchRequest;
import com.emma.springbootelasticsearch.elastic.model.GeoDistance;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;

public interface SearchService {
    /**
     * multi fields && regular page-nation
     * @param request
     * @return
     */
    SearchResponse queryString(ElasticSearchRequest request);

    /**
     * multi fields query && supports scrolling
     * @param request
     * @return
     */
    SearchResponse scrollQueryString(ElasticSearchRequest request);

    /**
     * specific searching
     * @param index
     * @param field
     * @param term
     */
    SearchResponse termSearch(String index, String field, String term);

    /**
     * Global querying
     *
     * @param index
     */
    SearchResponse matchAllSearch(String index);

    /**
     *  longitude & latitude querying
     * @param index
     * @param geo {longtitude & latitude}
     * @param pageNumber
     * @Param pageSize
     */
    SearchResponse geoDistanceSearch(String index, GeoDistance geo, Integer pageNumber, Integer pageSize);

    /**
     * searching nested objects
     *
     * @param path
     * @param  index
     * @param field
     * @param value
     * @param pageNumber
     * @param pageSize
     */
    SearchResponse matchNestedObjectSearch(String path, String index, String field, String value, Integer pageNumber, Integer pageSize);

    /**
     * join query: query parent value via child index
     * @param childType
     * @param index
     * @param field
     * @param value
     * @param pageNum
     * @param pageSize
     */
    SearchResponse hasChildSearch(String childType, String index, String field, String value, Integer pageNum, Integer pageSize);

    /**
     * join query: query child value via parent index
     * @param parentType
     * @param index
     * @param field
     * @param value
     * @param pageNum
     * @param pageSize
     */
    SearchResponse hasParentSearch(String parentType, String index, String field, String value, Integer pageNum, Integer pageSize);
}
