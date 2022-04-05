package meli.com.co.infrastructure.shared.handler;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.lang.reflect.Method;

@Produces
@Singleton
@Requires(classes = {Exception.class, ExceptionHandler.class})
class HttpExceptionsHandler implements ExceptionHandler<Exception, HttpResponse> {

    private static final String BUSINESS_TYPE = "BusinessValidation";
    private static final String TECHNICAL_TYPE = "TechnicalException";

    final Logger log= LoggerFactory.getLogger("Log");


    @Override
    public HttpResponse handle(HttpRequest request, Exception exception) {
        HttpStatus httpStatus;
        log.error(exception.getMessage());
        if(exception.getClass().toString().contains("BusinessException")){
            httpStatus= HttpStatus.valueOf(getExceptionStatusCode(exception));
            return HttpResponseFactory.INSTANCE.
                    status(httpStatus).
                    body(new HttpExceptionDto(BUSINESS_TYPE, exception.getMessage()));
        }else{
             httpStatus= HttpStatus.valueOf(500);
             return HttpResponseFactory.INSTANCE.
                    status(httpStatus).
                    body(new HttpExceptionDto(TECHNICAL_TYPE));
        }
    }

    private int getExceptionStatusCode(Exception exception){
        try {
            Method method = exception.getClass().getMethod("getStatusCode");
            Object value= method.invoke(exception);
            return Integer.valueOf((Integer) value);
        }catch (Exception ex){
            return 400;
        }
    }

}
