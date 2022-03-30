package meli.com.co.infrastructure.persistence;

import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import meli.com.co.domain.model.Cliente;
import meli.com.co.domain.service.dependency.DnaRepositoryI;
import meli.com.co.infrastructure.persistence.mongo.model.ClienteModel;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DnaRepository extends  Repository  implements DnaRepositoryI {

    @Inject
    private ModelMapper mapper;

    @Inject
    private MongoCollection<ClienteModel> clienteDao;


    public Mono<Boolean> saveDna(String [] dna, boolean isMutant){
        return Mono.just(isMutant);
    }


    public Mono<Boolean> guardarCliente(Cliente cliente){
        return Mono.just(mapper.map(cliente, ClienteModel.class)).flatMap(
          clienteModel-> Mono.from(clienteDao.insertOne(clienteModel))
         ).map(success-> true)
          .onErrorResume(error -> {
                   log.error(error.getMessage());
                   return Mono.error(new Exception(error));
         });
    }

    public  Mono<Cliente> consultarCliente(String identidad){
          return  Mono.from(clienteDao
                  .find(Filters.eq("identidad",identidad))
                  .first())
              .map(clienteModel -> mapper.map(clienteModel, Cliente.class))
              .onErrorResume(error -> {
                   log.error(error.getMessage());
                   return Mono.error(new Exception(error));
             });
    }

    public Flux<Cliente> consultarClientes() {
         return  Flux.from(clienteDao.find())
                 .map(clienteModel -> mapper.map(clienteModel,Cliente.class))
                 .onErrorResume(error -> {
                    log.error(error.getMessage());
                   return Mono.error(new Exception(error));
          });
    }

}
