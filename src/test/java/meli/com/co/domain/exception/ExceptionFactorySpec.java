package meli.com.co.domain.exception;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class ExceptionFactorySpec {

    @Test
    void get_business_exception_dna_no_mutant () {
        BusinessException businessException=ExceptionFactory.DNA_NO_MUTANT.get();
        assertEquals(businessException.getMessage(),"DNA_NO_MUTANT");
    }

    @Test
    void get_business_exception_invalid_dna () {
        BusinessException businessException=ExceptionFactory.INVALID_DNA.get();
        assertEquals(businessException.getMessage(),"INVALID_DNA");
    }
}