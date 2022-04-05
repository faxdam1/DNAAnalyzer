package meli.com.co.domain.service;


import io.micronaut.test.annotation.MicronautTest;

import meli.com.co.domain.exception.BusinessException;
import meli.com.co.domain.model.Stats;
import meli.com.co.domain.service.dependency.DnaRepositoryMock;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import javax.inject.Inject;


@MicronautTest
public class DnaServiceSpec {

    DnaService dnaService = new DnaService(new DnaAnalyzerService(), new DnaRepositoryMock());

    @Test
    void is_mutant() {
        String dna[] = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        StepVerifier
                .create(dnaService.isMutant(dna))
                .expectNext(true)
                .verifyComplete();
    }


    @Test
    void is_human() {
        String dna[] ={"ATGCGA", "CAGTGC","TTATTT", "AGACGG","GCGTCA","TCACTG"};
        StepVerifier
                .create(dnaService.isMutant(dna))
                .expectErrorMatches(throwable -> throwable instanceof BusinessException &&
                        throwable.getMessage().equals("DNA_NO_MUTANT")
                ).verify();
    }


    @Test
    void dna_character_invalid() {
        String dna[] ={"ATGCGA", "CAUTGC","TTATTT", "AGACGG","GCGTCA","TCACTG"};
        StepVerifier
                .create(dnaService.isMutant(dna))
                .expectErrorMatches(throwable -> throwable instanceof BusinessException &&
                        throwable.getMessage().equals("INVALID_DNA")
                ).verify();
    }

    @Test
    void dna_length_invalid() {
        String dna[] ={"ATGCGA", "CATGC","TTATTT", "AGACGG","GCGTCA","TCACTG"};
        StepVerifier
                .create(dnaService.isMutant(dna))
                .expectErrorMatches(throwable -> throwable instanceof BusinessException &&
                        throwable.getMessage().equals("INVALID_DNA")
                ).verify();
    }

    @Test
    void dna_get_state() {
        StepVerifier
                .create(dnaService.getStats())
                .expectNextMatches(stats->
                        stats.getCount_mutant_dna()==20&&
                        stats.getCount_human_dna()==40&&
                        stats.getRatio()==0.5)
                .verifyComplete();
    }



}








