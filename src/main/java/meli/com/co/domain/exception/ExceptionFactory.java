package meli.com.co.domain.exception;

public enum ExceptionFactory {

    INVALID_DNA("INVALID_DNA",400),
    DNA_NO_MUTANT("DNA_NO_MUTANT",403);

    private String message;
    private int statusCode;

    ExceptionFactory(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public BusinessException get(){
        return new BusinessException(this.message, this.statusCode);
    }

}