package meli.com.co.domain.service.dependency;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import meli.com.co.domain.model.Cliente;

public interface ClienteRepositoryI {

      Mono<Boolean> guardarCliente(Cliente cliente);
      Mono<Cliente> consultarCliente(String identidad);
      Flux<Cliente> consultarClientes();

}
