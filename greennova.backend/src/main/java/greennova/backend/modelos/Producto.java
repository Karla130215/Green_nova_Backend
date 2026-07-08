package greennova.backend.modelos;


public class Producto {

private Long idProducto;
private static long total = 0L;
private String nombre;
private String tipoProducto;
private Double precio;
private String imagen;
private String rLuz;
private String fRiego;
private String funcion;
private String descripcion;

public Producto(){
}// constructor vacio

public Producto (String nombre, String tipoProducto, Double precio, String imagen, String rLuz, String fRiego,String funcion,String descripcion ){

    Producto.total++;
    this.idProducto=Producto.total;
    this.nombre = nombre;
    this.tipoProducto = tipoProducto;
    this.precio = precio;
    this.imagen = imagen;
    this.rLuz = rLuz;
    this.fRiego = fRiego;
    this.funcion = funcion;
    this.descripcion = descripcion;
}// constructor Producto

    public Long getId() {
        return idProducto;
    }

    public void setId(Long id) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getrLuz() {
        return rLuz;
    }

    public void setrLuz(String rLuz) {
        this.rLuz = rLuz;
    }

    public String getfRiego() {
        return fRiego;
    }

    public void setfRiego(String fRiego) {
        this.fRiego = fRiego;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", tipoProducto='" + tipoProducto + '\'' +
                ", precio=" + precio +
                ", imagen='" + imagen + '\'' +
                ", rLuz='" + rLuz + '\'' +
                ", fRiego='" + fRiego + '\'' +
                ", funcion='" + funcion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }// to string
}// class producto
