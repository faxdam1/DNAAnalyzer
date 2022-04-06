package meli.com.co.infrastructure.shared;

import io.micronaut.test.annotation.MicronautTest;
import meli.com.co.infrastructure.shared.proxy.ElasticSearchClientProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}