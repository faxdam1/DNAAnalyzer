package meli.com.co.infrastructure.shared.handler;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;



@MicronautTest
public class HttpExceptionDtoSpec implements Serializable {


    @Test
    void init_http_exception_dto(){
        HttpExceptionDto httpExceptionDto=new HttpExceptionDto("BusinessException");
        Assertions.assertTrue(httpExceptionDto.getType().equals("BusinessException"));
    }

}