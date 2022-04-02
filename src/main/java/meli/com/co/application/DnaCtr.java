package meli.com.co.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import meli.com.co.domain.model.Stats;
import meli.com.co.domain.service.DnaService;
import meli.com.co.infrastructure.shared.dto.DnaDto;
import reactor.core.publisher.Mono;
import javax.inject.Inject;

@Controller("${micronaut.context-path}/dna")
public class DnaCtr extends Ctr {

    @Inject
    private DnaService dnaService;

    @Get(value ="stats", produces = MediaType.APPLICATION_JSON)
    public Mono<Stats> getStats() {
        return this.dnaService.getStats();
    }

    @Post(value ="mutant", produces = MediaType.APPLICATION_JSON)
    public Mono<Boolean> isMutant(@Body DnaDto dnaDto) {
        return Mono.just(dnaDto.getDna())
                .flatMap(dna -> this.dnaService.isMutant(dna));
    }

}




