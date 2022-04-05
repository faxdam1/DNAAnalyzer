package meli.com.co.domain.service.dependency;

import reactor.core.publisher.Mono;

public class DnaRepositoryMock implements DnaRepositoryI {
    public Mono<Boolean> saveDna(String [] dna, boolean isMutant){
         return Mono.just(true);
    }
    public Mono<Long> countMutant(){
        return Mono.just(new Long(20));
    }
    public Mono<Long> countHuman(){
        return Mono.just(new Long(40));
    }
}
