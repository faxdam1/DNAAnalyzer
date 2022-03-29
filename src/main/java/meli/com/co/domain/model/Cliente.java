package meli.com.co.domain.model;

import reactor.core.publisher.Mono;
import meli.com.co.domain.exception.ExceptionFactory;
import meli.com.co.domain.exception.Validate;
import java.util.Date;

public class Cliente extends Entity  {

    //LA UNICA FORMA DE CREAR UN MODELO DE DOMINIO ES MEDIANTE EL METODO CREATE//
    //SE APLICAN VALIDACIONES DE NEGOCIO (SMART-CONSTRUCTOR-PATTERN)
    //UTILIZAR VALIDATE CLASS SOLO PARA VALIDACIONES GENERICAS
    //EL CONSTRUCTOR VACIO ES PARA LOS CONVERSORES A JSON
    //NO UTILIZAR SETTERS RESPETAR "ENCAPSULAMIENTO", PARA MODIFICAR EL ESTADO, UTILIZAR METODOS DE NEGOCIO

    private String identidad;
    private String nombre;
    private Date fechaNacimiento;
    private Contacto contacto;

    public Cliente(){}
/*
    private Cliente(String identidad, String nombre, Date fechaNacimiento, Contacto contacto){
        this.identidad= identidad;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.contacto = contacto;
    }

    public static Mono<Cliente> create(String identidad, String nombre, Date fechaNacimiento, Contacto contacto){
        Cliente cliente =new Cliente(identidad, nombre, fechaNacimiento, contacto);
        return cliente.validate().then(Mono.just(cliente));
    }

    public Mono<Void> validate(){
        return Validate.nullOrEmptyValidate(identidad,"identidad")
                .switchIfEmpty(Validate.nullOrEmptyValidate(nombre,"nombre"))
                .switchIfEmpty(validarFechaNacimiento(fechaNacimiento))
                .switchIfEmpty(contacto.validate());
    }

    private Mono<Void> validarFechaNacimiento(Date fechaNacimiento){
        return Mono.just(fechaNacimiento.getTime()>=new Date().getTime())
                 .filter(it-> !it)
                 .switchIfEmpty(Mono.error(ExceptionFactory.DNA_NO_MUTANT.get()))
                 .then(Mono.empty());
    }*/

}
