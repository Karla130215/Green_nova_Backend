package greennova.backend.modelos;

public class DetallePedido {

private long idDetalle;
private static long total = 0L;
private int cantidad;
private double precioUnidad;
private Long idProducto;
private Long idPedido;

public DetallePedido(){
}// constructor vacio

public DetallePedido(int cantidad, double precioUnidad, Long idProducto, Long idPedido){
    DetallePedido.total++;
    this.idDetalle=DetallePedido.total;
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
