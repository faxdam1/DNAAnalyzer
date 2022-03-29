package meli.com.co.domain.service;

import meli.com.co.domain.exception.ExceptionFactory;
import reactor.core.publisher.Mono;


public class DnaAnalyzerService extends Service {







    //public Mono<Boolean> isMutant(String[] dna){


        //return Mono.just()
    //}



   /* public  Mono<Void>  rowValidate(String ) {
        return Mono.just(value).filter(it-> ("ATCG".contains(value)))
                .switchIfEmpty(Mono.error(ExceptionFactory.INVALID_DNA.get()))
                .then(Mono.empty());
    }*/

    public  Mono<Void>  characterValidate(String value) {
        return Mono.just(value).filter(it-> ("ATCG".contains(value)))
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








