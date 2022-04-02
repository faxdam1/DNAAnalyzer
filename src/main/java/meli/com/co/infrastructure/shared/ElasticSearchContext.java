package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import javax.inject.Singleton;

@Factory
public class ElasticSearchContext {

    @Value("${elasticsearch.endpoint}")
    private String endpoint;

    @Singleton
    RestHighLevelClient getRestHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost(endpoint, 80, "http")));
    }

}
