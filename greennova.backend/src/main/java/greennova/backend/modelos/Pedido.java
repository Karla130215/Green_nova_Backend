package greennova.backend.modelos;

import jakarta.persistence.*;

import java.util.List;

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

    // Relación ManyToOne: Muchos pedidos pertenecen a un usuario
    @ManyToOne
    @JoinColumn(name = "usuarios_id_usuario", nullable = false)
    private Usuario usuario;


public Pedido(){
}//clase vacia

public Pedido(String fechaPedido, Double total, String estado){

    this.fechaPedido = fechaPedido;
    this.total = total;
    this.estado = estado;

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
                ", usuario=" + usuario +
                '}';
    }
}// clase pedido
