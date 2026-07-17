package greennova.backend.modelos;



import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "detallePedido")
public class DetallePedido {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="idDetalle",unique=true,nullable=false)
private Long idDetalle;
@Column(name = "cantidad",nullable = false)
private int cantidad;
@Column(name = "precioUnidad",nullable = false)
private Double precioUnidad;
//Llaves Foraneas
private Long idProducto;
private Long idPedido;

public DetallePedido(){
}// constructor vacio

public DetallePedido(int cantidad, double precioUnidad, Long idProducto, Long idPedido){

    this.cantidad = cantidad;
    this.precioUnidad = precioUnidad;
    this.idProducto = idProducto;
    this.idPedido = idPedido;
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

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "idDetalle=" + idDetalle +
                ", cantidad=" + cantidad +
                ", precioUnidad=" + precioUnidad +
                ", idProducto=" + idProducto +
                ", idPedido=" + idPedido +
                '}';
    }// to string
}// clase Detalle Pedido
