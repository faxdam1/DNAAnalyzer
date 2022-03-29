package meli.com.co.domain.exception;

import reactor.core.publisher.Mono;


public class Validate {

    public static Mono<Void>  characterValidate(String value) {
        return Mono.just(value).filter(it-> ("ATCG".contains(value)))
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.empty());
    }
/*
    public static Mono<Void> nullEntityValidate(Object value, String field) {
        return Mono.just(value).filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()))
                .then(Mono.empty());
    }

    public static Mono<Void> mailValidate(String value, String field) {
        return Mono.just(Pattern.compile("^(.+)@(.+)$").matcher(value).matches())
                .filter(it-> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()))
                .then(Mono.empty());
    }*/

}
