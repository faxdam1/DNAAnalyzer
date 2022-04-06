package meli.com.co.infrastructure.persistence;

import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import meli.com.co.infrastructure.shared.proxy.ElasticSearchClientProxy;
import meli.com.co.infrastructure.shared.converter.StringArrayConverter;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentMatchers;
import reactor.test.StepVerifier;

import javax.inject.Inject;
import javax.inject.Singleton;

import static org.mockito.Mockito.*;


@MicronautTest
public class DnaRepositorySpec {

    @Inject
    DnaRepository dnaRepository;

    @Inject
    ElasticSearchClientProxy client;

    @Singleton
    ElasticSearchClientProxy getElasticSearchClientProxy() {
        return new ElasticSearchClientProxy();
    }

    @MockBean(ElasticSearchClientProxy.class)
    ElasticSearchClientProxy client() {
        return mock(ElasticSearchClientProxy.class);
    }

    @ParameterizedTest
    @CsvSource({"ATGCGA;CAGTGC;TTATGT;AGAAGG;CCCCTA;TCACTG,true", "AAAAGA;CCGTGC;TTATGT;AGGAGG;CCCCTA;TCACTG,true"})
    void save_dna(@ConvertWith(StringArrayConverter.class) String[] dna, Boolean isMutant) {
        doNothing().when(this.client).indexAsync(
                ArgumentMatchers.any(IndexRequest.class),
                ArgumentMatchers.any(RequestOptions.class),
                ArgumentMatchers.any(ActionListener.class));
        StepVerifier
                .create(dnaRepository.saveDna(dna,isMutant))
                .expectSubscription()
                .thenCancel()
                .verify();
    }

    @Test
    void count_mutant() {
        doNothing().when(this.client).searchAsync(
                ArgumentMatchers.any(SearchRequest.class),
                ArgumentMatchers.any(RequestOptions.class),
                ArgumentMatchers.any(ActionListener.class));
        StepVerifier
                .create(dnaRepository.countMutant())
                .expectSubscription()
                .thenCancel()
                .verify();
    }

    @Test
    void count_human() {
        doNothing().when(this.client).searchAsync(
                ArgumentMatchers.any(SearchRequest.class),
                ArgumentMatchers.any(RequestOptions.class),
                ArgumentMatchers.any(ActionListener.class));
        StepVerifier
                .create(dnaRepository.countHuman())
                .expectSubscription()
                .thenCancel()
                .verify();
    }


/*
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
    }*/

}
