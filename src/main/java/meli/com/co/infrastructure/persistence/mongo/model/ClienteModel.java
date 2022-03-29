package meli.com.co.infrastructure.persistence.mongo.model;
import java.io.Serializable;
import java.util.Date;

public class ClienteModel implements Serializable {

    private String identidad;
    private String nombre;
    private Date fechaNacimiento;
    private ContactoModel contacto;

    public String getIdentidad() {
        return identidad;
    }

    public void setIdentidad(String identidad) {
        this.identidad = identidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public ContactoModel getContacto() {
        return contacto;
    }

    public void setContacto(ContactoModel contacto) {
        this.contacto = contacto;
    }

}
