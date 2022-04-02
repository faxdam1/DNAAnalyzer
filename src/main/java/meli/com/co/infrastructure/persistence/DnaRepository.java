package meli.com.co.infrastructure.persistence;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
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
    RestHighLevelClient client;

    public Mono<Boolean> saveDna(String[] dna, boolean isMutant) {
        return Mono.fromCallable(() -> {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("dna", dna);
            jsonMap.put("isMutant", isMutant);
            IndexRequest indexRequest = new IndexRequest("dna", "doc").source(jsonMap);
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.status().getStatus() == 0;
        });
    }

    public Mono<Long> countMutant() {
        return Mono.fromCallable(() -> {
            SearchRequest searchRequest = new SearchRequest("dna");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("isMutant", true));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse.getHits().totalHits;
        });
    }

    public Mono<Long> countHuman() {
        return Mono.fromCallable(() -> {
            SearchRequest searchRequest = new SearchRequest("dna");
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery("isMutant", false));
            searchSourceBuilder.size(0);
            searchRequest.source(searchSourceBuilder);
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse.getHits().totalHits;
        });
    }

}
