package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import io.micronaut.test.annotation.MicronautTest;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.inject.Singleton;


@MicronautTest
public class ElasticSearchContextSpec {

    @Inject
    ElasticSearchContext elasticSearchContext;

    @Test
    void init_elastic_client() {
        ElasticSearchClientProxy client = elasticSearchContext.getElasticSearchClientProxy();
        Assertions.assertTrue(client != null);
    }

}