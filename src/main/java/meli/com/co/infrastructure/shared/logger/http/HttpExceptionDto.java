package meli.com.co.infrastructure.shared.logger.http;

import java.io.Serializable;

class HttpExceptionDto implements Serializable {

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