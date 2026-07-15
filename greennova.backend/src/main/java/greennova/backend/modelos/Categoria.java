package greennova.backend.modelos;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriaId", unique = true,nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private List<Producto> productos = new ArrayList<>();

    public Categoria (String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }//Constructor de categoria

    public Categoria(){
    }//constructor vacio

    public Long getId() {
        return id;
    }//getId

    public String getNombre() {
        return nombre;
    }//getNombre

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }//setNombre

    public String getDescripcion() {
        return descripcion;
    }//getDescripcion

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }//setDescripcion

    public List<Producto> getProductos() {
        return productos;
    }//getProductos

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }//toString
}//class Categoria
