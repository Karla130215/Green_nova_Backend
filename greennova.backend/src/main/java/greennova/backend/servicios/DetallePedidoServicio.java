package greennova.backend.servicios;

import greennova.backend.modelos.DetallePedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePedidoServicio {

    private final ArrayList<DetallePedido> listaDetalles = new ArrayList<>();

    // Obtener todos los detalles de pedido
    public List<DetallePedido> obtenerTodos() {
        return listaDetalles;
    }// obtener todos

    // Obtener detalle por ID
    public DetallePedido obtenerPorId(long id) {
        for (DetallePedido detalle : listaDetalles) {
            if (detalle.getIdDetalle() == id) {
                return detalle;
            }
        }
        return null;
    }// obtener por id

    // Guardar un nuevo detalle de pedido
    public DetallePedido guardar(DetallePedido detalle) {
        listaDetalles.add(detalle);
        return detalle;
    }// guardar

    // Actualizar un detalle existente
    public DetallePedido actualizar(long id, DetallePedido detalleActualizado) {
        for (int i = 0; i < listaDetalles.size(); i++) {
            if (listaDetalles.get(i).getIdDetalle() == id) {
                detalleActualizado.setIdDetalle(id);
                listaDetalles.set(i, detalleActualizado);
                return detalleActualizado;
            }
        }
        return null;
    }// actualizar

    // Eliminar un detalle por ID
    public boolean eliminar(long id) {
        for (int i = 0; i < listaDetalles.size(); i++) {
            if (listaDetalles.get(i).getIdDetalle() == id) {
                listaDetalles.remove(i);
                return true;
            }
        }
        return false;
    }// eliminar

}// clase DetallePedidoServicio
