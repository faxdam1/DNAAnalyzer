package meli.com.co.infrastructure.shared.handler;

import java.io.Serializable;

public class HttpExceptionDto implements Serializable {

    private String type;
    private String message;

    public HttpExceptionDto( String type, String message) {

        this.type = type;
        this.message = message;
    }

    public HttpExceptionDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}