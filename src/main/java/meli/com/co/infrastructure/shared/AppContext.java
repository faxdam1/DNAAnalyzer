package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
//import meli.com.co.domain.service.ClienteService;
import meli.com.co.domain.service.DnaService;
import meli.com.co.infrastructure.persistence.ClienteRepository;
import javax.inject.Inject;
import javax.inject.Singleton;


@Factory
public class AppContext {

    @Inject
    ClienteRepository clienteRepository;



    @Singleton
    DnaService getDnaService()
    {
        return new DnaService();
    }

}