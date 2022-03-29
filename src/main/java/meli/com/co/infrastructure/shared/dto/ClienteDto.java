package meli.com.co.infrastructure.shared.dto;
import java.io.Serializable;
import java.util.Date;

public class ClienteDto implements Serializable {

    private String identidad;
    private String nombre;
    private Date fechaNacimiento;
    private ContactoDto contacto;

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

    public ContactoDto getContacto() {
        return contacto;
    }

    public void setContacto(ContactoDto contacto) {
        this.contacto = contacto;
    }

}
