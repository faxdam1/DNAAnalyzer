package meli.com.co.infrastructure.service;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import meli.com.co.domain.model.Cliente;

@Client("http://misistema.com")
public interface ClienteService {

    @Get("/factura")
    Cliente conultarFactura();

}