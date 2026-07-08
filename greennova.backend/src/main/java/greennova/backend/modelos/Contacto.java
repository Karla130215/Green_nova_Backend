package greennova.backend.modelos;

import java.time.LocalDateTime;

public class Contacto {
private static long total = 0L;
private long idMensaje;
private String nombre;
private String apellido;
private String email;
private String mensaje;
private LocalDateTime fechaEnvio;

public Contacto (){
}//constructor vacio

public Contacto (String nombre, String apellido, String email, String mensaje){
    Contacto.total++;
    this.idMensaje=Contacto.total;
    this.nombre = nombre;
    this.apellido = apellido;
    this.email = email;
    this.mensaje = mensaje;
    this.fechaEnvio = LocalDateTime.now();
}//constructor

    public long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaPedido() {
        return fechaEnvio;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaEnvio = fechaPedido;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "idMensaje=" + idMensaje +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fechaPedido=" + fechaEnvio +
                '}';
    }//to string
}// clase contacto
