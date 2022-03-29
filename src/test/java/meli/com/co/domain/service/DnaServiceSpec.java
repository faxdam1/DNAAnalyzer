package meli.com.co.domain.service;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import meli.com.co.domain.exception.BusinessException;
import meli.com.co.domain.exception.ExceptionFactory;
import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;
import javax.inject.Inject;



@MicronautTest
public class DnaServiceSpec {

    @Inject
    DnaService dnaService;

    @Test
    void invalid_character() {
        StepVerifier
                .create(dnaService.characterValidate("Z"))
                .expectErrorMatches(throwable -> throwable instanceof BusinessException &&
                        throwable.getMessage().equals("INVALID_DNA")
                ).verify();
    }

    @Test
    void valid_character() {
        StepVerifier
                .create(dnaService.characterValidate("A"))
                .expectComplete().verify();
    }



}








