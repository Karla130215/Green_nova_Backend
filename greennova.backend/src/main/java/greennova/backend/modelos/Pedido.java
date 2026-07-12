package greennova.backend.modelos;

import jakarta.persistence.*;

@Entity
@Table(name="pedido")
public class Pedido {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pedidoId",unique=true,nullable = false)
private Long idPedido;
@Column(name = "fechaPedido",nullable = false)
private String fechaPedido;
@Column(name = "total",nullable = false)
private Double total;
@Column(name = "estado",nullable = false)
private String estado;
// llave foranea
@ManyToOne(fetch = FetchType.LAZY) // LAZY evita cargar el usuario si no lo necesitas, mejorando rendimiento
@JoinColumn(name = "id_usuario", nullable = false)
private long idUsuario;


public Pedido(){
}//clase vacia

public Pedido(String fechaPedido, Double total, String estado){

    this.fechaPedido = fechaPedido;
    this.total = total;
    this.estado = estado;
    this.idUsuario = idUsuario;
}//clase pedido

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", fechaPedido='" + fechaPedido + '\'' +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }// to string
}// clase pedido
