package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import meli.com.co.infrastructure.shared.proxy.ElasticSearchClientProxy;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

import javax.inject.Singleton;


@Factory
public class PersistenceContext {

    @Value("${elasticsearch.endpoint}")
    private String endpoint;
    @Value("${elasticsearch.user}")
    private String user;
    @Value("${elasticsearch.password}")
    private String password;

    @Value("${sqs.accesskey}")
    private String accessKey;

    @Value("${sqs.secretaccesskey}")
    private String secretAccessKey;



    @Singleton
    ElasticSearchClientProxy getElasticSearchClientProxy(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(user, password));
        RestClientBuilder builder = RestClient.builder(new HttpHost(endpoint,443,"https"))
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                });
       return new ElasticSearchClientProxy(new RestHighLevelClient(builder));
    }


    @Singleton
    public SnsAsyncClient getSnsAsyncClient() {
        return SnsAsyncClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return accessKey;
                    }

                    @Override
                    public String secretAccessKey() {
                        return secretAccessKey;
                    }
                }))
                .build();
    }

}
