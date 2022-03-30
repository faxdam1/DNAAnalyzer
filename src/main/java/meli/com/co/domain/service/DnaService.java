package meli.com.co.domain.service;

import meli.com.co.infrastructure.persistence.DnaRepository;
import reactor.core.publisher.Mono;
import meli.com.co.domain.exception.ExceptionFactory;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DnaService extends Service {

    private DnaAnalyzerService dnaAnalyzerService;
    private DnaRepository dnaRepository;

    public DnaService(DnaAnalyzerService dnaAnalyzerService, DnaRepository dnaRepository) {
        this.dnaAnalyzerService = dnaAnalyzerService;
        this.dnaRepository = dnaRepository;
    }

    public Mono<Boolean> isMutant(String[] dna) {
        return this.dnaValidate(dna)
                .then(Mono.just(this.dnaAnalyzerService.isMutant(dna)))
                .flatMap(it -> {
                    this.dnaRepository.saveDna(dna, it);
                    return Mono.just(it);
                })
                .filter(it -> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()))
                .onErrorResume(error -> {
                    log.error(error.getMessage());
                    return Mono.error(error);
                });
    }


    public static Mono<Void> dnaValidate(String dna[]) {
        return Mono.just(dna).map(it -> {

            boolean d=Arrays.stream(dna).allMatch(row -> Pattern.compile("[ACTG]/gy").matcher(row).matches());
            System.out.println(d);
            return d;
        }
        ).filter(it -> it)
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.empty());
    }

    //public  Mono<Void> dnaValidate(String[] dna) {
    //    return Mono.just(dna).filter(it-> ("ATCG".contains(value)))
    //            .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
    //            .then(Mono.empty());
    //}



   /* public  Mono<Void>  rowValidate(String ) {
        return Mono.just(value).filter(it-> ("ATCG".contains(value)))
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.empty());
    }*/

    public Mono<Void> characterValidate(String value) {
        return Mono.just(value).filter(it -> ("ATCG".contains(value)))
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.empty());
    }


    /*
    private ClienteRepositoryI clienteRepository;

    public ClienteService(ClienteRepositoryI clienteRepository){
        this.clienteRepository=clienteRepository;
    }

    public Mono<Boolean> guardarCliente(final Cliente cliente) {
        return Validate.nullEntityValidate(cliente, "Cliente")
                .switchIfEmpty(cliente.validate())
                .then(clienteRepository.guardarCliente(cliente))
                .onErrorResume(error-> {
                     log.error(error.getMessage());
                     return Mono.error(error);
                 });
    }

    public Mono<Cliente> consultarCliente(final String identidad) {
        return Validate.nullOrEmptyValidate(identidad, "identidad")
                .then(clienteRepository.consultarCliente(identidad))
                .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()))
                .onErrorResume(error-> {
                      log.error(error.getMessage());
                      return Mono.error(error);
                });
    }

    public Flux<Cliente> consultarClientes() {
        return clienteRepository.consultarClientes();
    }
    */


}








