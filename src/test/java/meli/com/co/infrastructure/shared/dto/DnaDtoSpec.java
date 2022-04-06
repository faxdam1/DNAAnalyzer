package meli.com.co.infrastructure.shared.dto;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.Serializable;


@MicronautTest
public class DnaDtoSpec implements Serializable {

    @Test
    void init_dna_dto(){
        String [] dna=new String[] {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
       DnaDto dnaDto= new DnaDto(dna,true);
        Assertions.assertEquals(dna,dnaDto.getDna());
    }

    @Test
    void init_empty_dna_dto(){
        DnaDto dnaDto= new DnaDto();
    }

    @Test
    void set_dna_in_dto(){
        DnaDto dnaDto= new DnaDto();
        String [] dna=new String[] {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        dnaDto.setDna(dna);
        Assertions.assertEquals(dnaDto.getDna(),dna);
    }

    @Test
    void set_ismutant_in_dto(){
        DnaDto dnaDto= new DnaDto();
        dnaDto.setMutant(true);
        Assertions.assertTrue(dnaDto.isMutant());
    }


}
