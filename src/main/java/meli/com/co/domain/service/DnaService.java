package meli.com.co.domain.service;

import meli.com.co.domain.model.Stats;
import meli.com.co.domain.service.dependency.DnaRepositoryI;
import reactor.core.publisher.Mono;
import meli.com.co.domain.exception.ExceptionFactory;
import java.util.Arrays;

public class DnaService extends Service {

    private DnaAnalyzerService dnaAnalyzerService;
    private DnaRepositoryI dnaRepositoryI;

    public DnaService(DnaAnalyzerService dnaAnalyzerService, DnaRepositoryI dnaRepository) {
        this.dnaAnalyzerService = dnaAnalyzerService;
        this.dnaRepositoryI = dnaRepository;
    }

    public Mono<Boolean> isMutant(String[] dna) {
        return this.isDnaValid(dna)
                .flatMap(it->Mono.just(this.dnaAnalyzerService.isMutant(dna)))
                .flatMap(mutant->this.dnaRepositoryI.saveDna(dna, mutant).flatMap(it->Mono.just(mutant)))
                .filter(it -> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()));
    }

    public Mono<Boolean> isDnaValid(String dna[]) {
        return Mono.just(dna).filter(it -> Arrays.stream(it).allMatch(row ->row.length() == dna.length&&
                Arrays.stream(row.split("")).allMatch(letter -> "ATCG".contains(letter))

        ))
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.just(true));
    }

    public Mono<Stats> getStats() {
        return this.dnaRepositoryI.countMutant().flatMap(countMutant->
             this.dnaRepositoryI.countHuman().flatMap(countHuman->
                 Mono.just(new Stats(countMutant,countHuman))
             ));
    }


}








