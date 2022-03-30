package meli.com.co.application;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class DnaCtrSpec {

    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/ms-cliente")
    HttpClient client;

    @Test
    void testHelloWorldResponse() {
        String response = client.toBlocking().retrieve(HttpRequest.GET("/cliente"));
        assertEquals("Hello World", response); //)
    }
}
