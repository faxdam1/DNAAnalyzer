package meli.com.co.domain.exception;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class BusinessExceptionSpec  {

    @Test
    void create_business_exception() {
        BusinessException businessException= new BusinessException("INVALID_DNA",400);
        assertEquals(businessException instanceof RuntimeException , true);
    }

    @Test
    void get_status(){
       BusinessException businessException= new BusinessException("INVALID_DNA",400);
       assertEquals(businessException.getStatusCode() , 400);
    }
}


