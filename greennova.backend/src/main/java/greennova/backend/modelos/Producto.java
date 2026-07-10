package greennova.backend.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoId", unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipoProducto", nullable = false)
    private String tipoProducto;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "rLuz", nullable = false)
    private String rLuz;

    @Column(name = "fRiego", nullable = false)
    private String fRiego;

    @Column(name = "funcion", nullable = false)
    private String funcion;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

public Producto (String nombre, String tipoProducto, Double precio, String imagen, String rLuz, String fRiego,String funcion,String descripcion ){
    this.nombre = nombre;
    this.tipoProducto = tipoProducto;
    this.precio = precio;
    this.imagen = imagen;
    this.rLuz = rLuz;
    this.fRiego = fRiego;
    this.funcion = funcion;
    this.descripcion = descripcion;
}// constructor Producto

    public Producto(){
    }// constructor vacio (requisito JPA)

    public Long getId() {
        return id;
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
}// class producto
