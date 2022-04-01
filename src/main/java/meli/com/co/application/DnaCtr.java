package meli.com.co.application;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import meli.com.co.domain.model.Stats;
import meli.com.co.domain.service.DnaService;
import meli.com.co.infrastructure.shared.dto.DnaDto;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import javax.inject.Inject;

@Controller("${micronaut.context-path}/dna")
public class DnaCtr extends Ctr {

    @Inject
    private ModelMapper mapper;

    @Inject
    private DnaService dnaService;

    @Get(value ="stats”", produces = MediaType.APPLICATION_JSON)
    public Mono<Stats> stats() {

        return this.dnaService.getStats();
    }
    @Post(value ="mutant", produces = MediaType.APPLICATION_JSON)
    public Mono<Boolean> isMutant(@Body DnaDto dnaDto) {
        return Mono.just(dnaDto.getDna())
                .flatMap(dna -> this.dnaService.isMutant(dna));
    }







   /* public Publisher<HttpResponse<ClientDto>> queryClient(@PathVariable Publisher<String> dni) {
        return Mono.just(dni)
                .flatMap(it -> clientService.queryClient(it)).thenReturn(it-> )
                .map(it -> {
                    ClientDto result = mapper.map(it, ClientDto.class);
                    System.out.println(result);
                    return HttpResponse.ok(result);
                });
    }*/


    /*
    @Inject
    private ClienteService clienteService;

    //OJO EL CONTROLADOR PUEDE RECIBIR UN DTO, PERO
    //SIEMPRE DEBE ENVIAR UNA ENTIDAD DE DOMINIO AL SERVICIO DE DOMINIO

    @Post(value ="guardar", produces = MediaType.APPLICATION_JSON)
    public Mono<Boolean> guardarCliente(@Body ClienteDto clienteDto) {
         return Mono.just(mapper.map(clienteDto, Cliente.class))
                 .flatMap(cliente -> clienteService.guardarCliente(cliente));
    }

    @Version("1")
    @Get(value="consultar", produces = MediaType.APPLICATION_JSON)
    public Mono<ClienteDto> consultarCliente(@Parameter String identidad) {
        return clienteService.consultarCliente(identidad)
                .map(it-> mapper.map(it,ClienteDto.class));
    }

    @Version("1")
    @Get(value="listar", produces = MediaType.APPLICATION_JSON)
    public Mono<List<ClienteDto>> listarClientes() {
        return clienteService.consultarClientes()
                .map(it-> mapper.map(it,ClienteDto.class))
                .collectList();
    }

    @Version("2")
    @Get(value="listar", produces = MediaType.APPLICATION_JSON)
    public Mono<List<ClienteDto>> listarClientesOld() {
        return clienteService.consultarClientes()
                .map(it-> mapper.map(it,ClienteDto.class))
                .collectList();
    }

    //Existe un problema con Flux en AWS, pero ya esta reportado el bug. Se esta solucionando
    @Get(value="listar2", produces = MediaType.APPLICATION_JSON)
    public Flux<ClienteDto> listarClientes2() {
        return clienteService.consultarClientes()
                .map(it-> mapper.map(it,ClienteDto.class));
    }*/

    @Get(produces = MediaType.APPLICATION_JSON)
    public Mono<Stats> index() {
        return this.dnaService.getStats();
    }

}




