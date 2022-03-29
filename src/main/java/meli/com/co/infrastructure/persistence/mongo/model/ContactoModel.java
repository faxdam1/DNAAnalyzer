package meli.com.co.infrastructure.persistence.mongo.model;

import java.io.Serializable;

public class ContactoModel implements Serializable {

    private String mail;
    private String telefono;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
