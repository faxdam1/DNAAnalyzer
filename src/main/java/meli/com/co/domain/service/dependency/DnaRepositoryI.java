package meli.com.co.domain.service.dependency;

import reactor.core.publisher.Mono;

public interface DnaRepositoryI {

      Mono<Boolean> saveDna(String [] dna, boolean isMutant);
      Mono<Boolean> sendDna(String [] dna, boolean isMutant);
      Mono<Long> countMutant();
      Mono<Long> countHuman();

}
