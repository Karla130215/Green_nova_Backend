package greennova.backend.modelos;

public class Pedido {

private long idPedido;
private static long totalid = 0L;
private String fechaPedido;
private Double total;
private String estado;
// llave foranea
private Long idUsuario;


public Pedido(){
}//clase vacia

public Pedido(String fechaPedido, Double total, String estado){
    Pedido.totalid++;
    this.idPedido=Pedido.totalid;
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
