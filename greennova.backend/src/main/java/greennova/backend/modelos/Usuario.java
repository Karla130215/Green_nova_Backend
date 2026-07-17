package greennova.backend.modelos;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name ="usuario" )
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario",unique = true,nullable = false)
private long idUsuario;
    @Column(name = "total",nullable = false)
private static long total = 0L;
    @Column(name = "nombre",nullable = false)
private String nombre;
    @Column(name = "telefono",nullable = false)
private String telefono;
    @Column(name = "email",nullable = false)
private String email;
    @Column(name = "password",nullable = false)
private String password;

//llave foranea
// Relación: Un usuario tiene muchos pedidos
@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
private List<Pedido> pedidos;

public  Usuario(){
}//constructor vacio

public  Usuario(String nombre, String telefono, String email, String password){
    Usuario.total++;
    this.idUsuario=Usuario.total;
    this.nombre = nombre;
    this.telefono = telefono;
    this.email = email;
    this.password = password;

}//constructor

    public long getId() {
        return idUsuario;
    }

    public void setId(long idUsuario) {
        this.idUsuario = idUsuario;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}// clase Usuario
