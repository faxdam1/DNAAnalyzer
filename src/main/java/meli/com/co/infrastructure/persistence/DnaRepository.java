package meli.com.co.infrastructure.persistence;

import meli.com.co.infrastructure.shared.ElasticSearchClientProxy;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import reactor.core.publisher.Mono;
import meli.com.co.domain.service.dependency.DnaRepositoryI;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;


@Singleton
public class DnaRepository extends Repository implements DnaRepositoryI {

    @Inject
    ElasticSearchClientProxy client;

    public Mono<Boolean> saveDna(String[] dna, boolean isMutant) {
        return Mono.create(sink -> {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("dna", dna);
            jsonMap.put("isMutant", isMutant);
            IndexRequest indexRequest = new IndexRequest("dna", "doc").source(jsonMap);
            client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {
                    sink.success(indexResponse.status().getStatus()==0);
                }
                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    sink.error(e);
                }
            });
        });
    }





    public Mono<Long> countMutant() {
        return Mono.create(sink -> {
            SearchRequest searchRequest = new SearchRequest("dna");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("isMutant", true));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            client.searchAsync(searchRequest, RequestOptions.DEFAULT,new ActionListener<SearchResponse>() {
                @Override
                public void onResponse(SearchResponse searchResponse) {
                    sink.success(searchResponse.getHits().totalHits);
                }
                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    sink.error(e);
                }
            });
        });
    }

    public Mono<Long> countHuman() {
        return Mono.create(sink -> {
            SearchRequest searchRequest = new SearchRequest("dna");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("isMutant", false));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            client.searchAsync(searchRequest, RequestOptions.DEFAULT,new ActionListener<SearchResponse>() {
                @Override
                public void onResponse(SearchResponse searchResponse) {
                    sink.success(searchResponse.getHits().totalHits);
                }
                @Override
                public void onFailure(Exception e) {
                    e.printStackTrace();
                    sink.error(e);
                }
            });
        });
    }

}
