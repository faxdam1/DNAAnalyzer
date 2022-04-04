package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import javax.inject.Singleton;


@Factory
public class ElasticSearchContext {

    @Value("${elasticsearch.endpoint}")
    private String endpoint;
    @Value("${elasticsearch.user}")
    private String user;
    @Value("${elasticsearch.password}")
    private String password;

    @Singleton
    RestHighLevelClient getRestHighLevelClient(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(user, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(endpoint,443,"https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });

       return new RestHighLevelClient(builder);
    }

}
