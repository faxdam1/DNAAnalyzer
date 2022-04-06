package meli.com.co.infrastructure.shared;

import io.micronaut.test.annotation.MicronautTest;
import meli.com.co.infrastructure.shared.proxy.ElasticSearchClientProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.sns.SnsAsyncClient;

import javax.inject.Inject;


@MicronautTest
public class PersistenceContextSpec {

    @Inject
    PersistenceContext persistenceContext;

    @Test
    void init_elastic_client() {
        ElasticSearchClientProxy client = persistenceContext.getElasticSearchClientProxy();
        Assertions.assertTrue(client != null);
    }

    @Test
    void init_sns_client() {
        SnsAsyncClient client = persistenceContext.getSnsAsyncClient();
        Assertions.assertTrue(client != null);
    }


}