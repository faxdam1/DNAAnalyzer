package meli.com.co.infrastructure.shared;

import io.micronaut.context.annotation.Factory;
import meli.com.co.domain.service.DnaAnalyzerService;
import meli.com.co.domain.service.DnaService;
import meli.com.co.infrastructure.persistence.DnaRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

//import meli.com.co.domain.service.ClienteService;


@Factory
public class AppContext {

    @Inject
    DnaRepository dnaRepository;


    @Singleton
    DnaService getDnaService()
    {
        return new DnaService( new DnaAnalyzerService(),dnaRepository);
    }

}