package meli.com.co.application;

import com.google.gson.Gson;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import meli.com.co.domain.exception.BusinessException;
import meli.com.co.domain.exception.ExceptionFactory;
import meli.com.co.domain.model.Stats;
import meli.com.co.domain.service.DnaAnalyzerService;
import meli.com.co.domain.service.DnaService;
import meli.com.co.infrastructure.persistence.DnaRepository;
import meli.com.co.infrastructure.shared.converter.StringArrayConverter;
import meli.com.co.infrastructure.shared.dto.DnaDto;
import meli.com.co.infrastructure.shared.handler.HttpExceptionDto;
import org.apache.http.HttpException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.converter.ConvertWith;
import reactor.core.publisher.Mono;
import javax.inject.Inject;
import javax.inject.Singleton;
import static org.mockito.Mockito.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import reactor.test.StepVerifier;

import java.lang.reflect.Method;

@MicronautTest
class DnaCtrSpec {

    @Inject
    @Client("/dna-analyzer/dna")
    HttpClient client;

    @Inject
    DnaRepository dnaRepository;
    @Inject
    DnaAnalyzerService dnaAnalyzerService;
    @Inject
    DnaService dnaService;
    @Singleton
    DnaService getDnaService()
    {
        return new DnaService(dnaAnalyzerService,dnaRepository);
    }
    @MockBean(DnaRepository.class)
    DnaRepository dnaRepository() {
        return mock(DnaRepository.class);
    }

    @ParameterizedTest
    @CsvSource({"20,40,0.5", "30,60,0.5", "50,100,0.5", "50,0,-1"})
    void get_stats(Long count_mutant_dna, Long count_human_dna, Float ratio) {
        when(dnaRepository.countMutant())
                .thenReturn(Mono.just(count_mutant_dna));
        when(dnaRepository.countHuman())
                .thenReturn(Mono.just(count_human_dna));

        String response = client.toBlocking().retrieve(HttpRequest.GET("/stats"));
        Stats statsRes=new Gson().fromJson(response,Stats.class);
        Assertions.assertTrue(statsRes.getCount_human_dna().equals(count_human_dna)&&
                        statsRes.getCount_mutant_dna().equals(count_mutant_dna)&&
                        statsRes.getRatio().equals(ratio)
                );
    }

    @ParameterizedTest
    @CsvSource({"ATGCGA;CAGTGC;TTATGT;AGAAGG;CCCCTA;TCACTG,true","AAAAGA;CCGTGC;TTATGT;AGGAGG;CCCCTA;TCACTG,true"})
    void is_mutant(@ConvertWith(StringArrayConverter.class) String[] dna, Boolean isMutant) {
        when(dnaRepository.sendDna(dna,isMutant))
                .thenReturn(Mono.just(true));
        String response = client.toBlocking().retrieve(HttpRequest.POST("/mutant",new DnaDto(dna)));
        Boolean isMutantRes=new Gson().fromJson(response,Boolean.class);
        Assertions.assertTrue(isMutantRes==isMutant);
    }

    @ParameterizedTest
    @CsvSource({"ATGCGA;CAGTGC;TTATGT;AGAAGG;CCCCTA;TCACTG,true","AAAAGA;CCGTGC;TTATGT;AGGAGG;CCCCTA;TCACTG,true"})
    void is_mutant_status_code_200(@ConvertWith(StringArrayConverter.class) String[] dna, Boolean isMutant) {
        when(dnaRepository.saveDna(dna,isMutant))
                .thenReturn(Mono.just(true));
        StepVerifier
                .create(client.exchange(
                        HttpRequest.POST("/mutant",new DnaDto(dna)),
                        Argument.of(HttpExceptionDto.class)
                ))
                .assertNext(res->
                        res.getStatus().equals(HttpStatus.OK)
                );
    }

    @ParameterizedTest
    @CsvSource({"ATGCGA;CAGTGC;TTATTT;AGACGG;GCGTCA;TCACTG,false","ATGCGA;TTATTT;CAGTGC;GCGTCA;AGACGG;TCACTG,false"})
    void is_not_mutant_status_code_403(@ConvertWith(StringArrayConverter.class) String[] dna, Boolean isMutant) {
        when(dnaRepository.saveDna(dna,isMutant))
                .thenReturn(Mono.just(true));
        StepVerifier
                .create(client.exchange(
                        HttpRequest.POST("/mutant",new DnaDto(dna)),
                        Argument.of(HttpExceptionDto.class)
                ))
                .assertNext(res->
                        res.getStatus().equals(HttpStatus.FORBIDDEN)
                );
    }


    @ParameterizedTest
    @CsvSource({"ATUCGA;CAGTGC;TTATGT;AGAAGG;CCCCTA;TCACTG,true","ZAAAGA;CCGTGC;TTATGT;AGGAGG;CCCCTA;TCACTG,true","AAGA;CCGTGC;TTATGT;AGGAGG;CCCCTA;TCACTG,true"})
    void is_not_valid_dna(@ConvertWith(StringArrayConverter.class) String[] dna, Boolean isMutant) {
        when(dnaRepository.saveDna(dna,isMutant))
                .thenReturn(Mono.just(true));

        StepVerifier
                .create(client.retrieve(HttpRequest.POST("/mutant",new DnaDto(dna))))
                .expectErrorMatches(err-> err.getMessage().equals(ExceptionFactory.INVALID_DNA.get().getMessage())).verify();
    }


}
