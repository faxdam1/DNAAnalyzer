package meli.com.co.infrastructure.persistence.dao;

import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import meli.com.co.infrastructure.shared.dto.QueryMutantDto;
import meli.com.co.infrastructure.shared.dto.ResponseDto;
import meli.com.co.infrastructure.shared.dto.DnaDto;

@Client("${endpointdb}")
public interface DnaDao {

    @Post("/dna/_doc")
    ResponseDto indexDna(DnaDto dnaDto);

    @Post("/dna/_search")
    ResponseDto countDna(QueryMutantDto queryMutantDto);

}