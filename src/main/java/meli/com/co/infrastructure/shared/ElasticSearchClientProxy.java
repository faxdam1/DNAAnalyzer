package meli.com.co.infrastructure.shared;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchClientProxy {

    private RestHighLevelClient client;

    public ElasticSearchClientProxy() { }

    public ElasticSearchClientProxy(RestHighLevelClient client) {
        this.client = client;
    }

    public void searchAsync(SearchRequest searchRequest, RequestOptions requestOptions, ActionListener<SearchResponse> listener) {
        client.searchAsync(searchRequest, requestOptions, listener);
    }

    public void indexAsync(IndexRequest indexRequest, RequestOptions requestOptions, ActionListener<IndexResponse> listener) {
        client.indexAsync(indexRequest, requestOptions, listener);
    }

}
