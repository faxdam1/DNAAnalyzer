package meli.com.co.domain.service;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
public class DnaAnalyzerServiceSpec extends Service {

    DnaAnalyzerService dnaAnalyzerService=new DnaAnalyzerService();

    @Test
    public void is_mutant() {
        String dna[]={"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        assertTrue( dnaAnalyzerService.isMutant(dna));
    }

    @Test
    public void is_human() {
        String dna[]={ "ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"};
        assertFalse( dnaAnalyzerService.isMutant(dna));
    }



}








