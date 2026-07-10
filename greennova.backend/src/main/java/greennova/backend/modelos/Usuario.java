package greennova.backend.modelos;

public class Usuario {
private long idUsuario;
private static long total = 0L;
private String nombre;
private String telefono;
private String email;
private String password;

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
