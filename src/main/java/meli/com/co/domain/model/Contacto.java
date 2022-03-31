package meli.com.co.domain.model;


public class Contacto extends ValueObject {

    private String mail;
    private String telefono;

    public Contacto(){}
/*
    private Contacto(String mail, String telefono){
        this.mail = mail;
        this.telefono = telefono;
    }

    public static Mono<Contacto> create(String mail, String telefono){
        Contacto contacto =new Contacto(mail, telefono);
        return contacto.validate().then(Mono.just(contacto));
    }

    public Mono<Void> validate(){
        return Validate.mailValidate(mail,"mail");
    }

    public String getMail() {
        return mail;
    }

    public String getTelefono() {
        return telefono;
    }
*/
}
