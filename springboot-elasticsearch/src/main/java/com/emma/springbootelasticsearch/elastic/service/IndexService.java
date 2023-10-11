package com.emma.springbootelasticsearch.elastic.service;

import org.elasticsearch.common.xcontent.XContentBuilder;

import java.util.List;
import java.util.Map;

public interface IndexService {
    /**
     * create index mapping
     * @param indexName
     * @param mapping
     */
    void createMapping(String indexName, XContentBuilder mapping);

    /**
     * index one document
     * @param indexName
     * @param indexType
     * @param doc
     */
    void indexDoc(String indexName, String indexType, Map<String, Object> doc);

    /**
     * use route to index document
     * @param indexName
     * @param indexType
     * @param doc
     */
    void indexDocWithRouting(String indexName, String indexType, String route, Map<String, Object> doc);

    /**
     * index a series of docs
     * @param indexName
     * @param indexType
     * @param docs
     */
    void indexDocs(String indexName, String indexType, List<Map<String, Object>> docs);

    /**
     *  index support routing a series of Docs
     *
     * @param indexName
     * @param indexType
     * @param route
     * @param docs
     */
    void indexDocsWithRouting(String indexName, String indexType, String route, List<Map<String, Object>> docs);

    /**
     * delete a piece of doc
     * @param indexName
     * @param indexType
     * @param id
     * @return
     */
    int deleteDoc(String indexName, String indexType, String id);

    /**
     * modify a piece of doc
     * @param indexName
     * @param indexType
     * @param doc
     */
    void updateDoc(String indexName, String indexType, Map<String, Object> doc);

    /**
     *  check whether an index really exists.
     * @param indexName
     * @return
     */
    boolean existIndex(String indexName); 


}
