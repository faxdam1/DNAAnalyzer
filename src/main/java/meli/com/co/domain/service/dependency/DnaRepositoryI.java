package meli.com.co.domain.service.dependency;

import reactor.core.publisher.Mono;

public interface DnaRepositoryI {


      Mono<Boolean> saveDna(String [] dna, boolean isMutant);
      Mono<Integer> countMutant();

}
