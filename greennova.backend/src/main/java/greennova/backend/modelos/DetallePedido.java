package greennova.backend.modelos;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "detallePedido")

public class DetallePedido {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "idDetalle", unique = true, nullable = false)
 private Long idDetalle;

@Column(name = "cantidad",nullable = false)
private int cantidad;
@Column(name = "precioUnidad",nullable = false)
private Double precioUnidad;
//Llaves Foraneas

    // Relación: Muchos detalles de pedido apuntan a un único Producto
    @ManyToOne
    @JoinColumn(name = "productos_id_productos")
    private Producto producto;


public DetallePedido(){
}// constructor vacio

public DetallePedido(int cantidad, double precioUnidad){

    this.cantidad = cantidad;
    this.precioUnidad = precioUnidad;

}// constructor

    public long getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(long idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }


// to string
}// clase Detalle Pedido
