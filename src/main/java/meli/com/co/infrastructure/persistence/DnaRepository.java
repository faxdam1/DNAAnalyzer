package meli.com.co.infrastructure.persistence;

import meli.com.co.infrastructure.persistence.dao.DnaDao;
import meli.com.co.infrastructure.shared.dto.DnaDto;
import meli.com.co.infrastructure.shared.dto.QueryMutantDto;
import meli.com.co.infrastructure.shared.dto.ResponseDto;
import reactor.core.publisher.Mono;
import meli.com.co.domain.service.dependency.DnaRepositoryI;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DnaRepository extends  Repository  implements DnaRepositoryI {

   @Inject
   private DnaDao dnaDao;

    public Mono<Boolean> saveDna(String [] dna, boolean isMutant){
        ResponseDto response= dnaDao.indexDna(new DnaDto(dna,isMutant));
        return Mono.just(response.equals("created"));
    }

    public Mono<Integer> countMutant(){
        QueryMutantDto queryMutant=new QueryMutantDto(true);
        ResponseDto response= dnaDao.countDna(queryMutant);
        return Mono.just(response.getTotal());
    }

    public Mono<Integer> countHuman(){
        QueryMutantDto queryMutant=new QueryMutantDto(false);
        ResponseDto response= dnaDao.countDna(queryMutant);
        return Mono.just(response.getTotal());
    }

}
