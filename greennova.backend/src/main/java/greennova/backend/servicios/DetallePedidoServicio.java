package greennova.backend.servicios;

import greennova.backend.modelos.DetallePedido;
import greennova.backend.repositorios.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetallePedidoServicio {

  //  private final ArrayList<DetallePedido> listaDetalles = new ArrayList<>();
  private final DetallePedidoRepository detallePedidoRepository;
    @Autowired
    public DetallePedidoServicio(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    // Obtener todos los detalles de pedido
    public List<DetallePedido> obtenerTodos() {

        return detallePedidoRepository.findAll();
    }// obtener todos

    // Obtener detalle por ID
    public DetallePedido obtenerPorId(long id) {

        return detallePedidoRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("El Detalle Pedido con el id [" + id + "] no existe") );

    }// obtener por id

    // Guardar un nuevo detalle de pedido
    public DetallePedido guardar(DetallePedido detalle) {
        return detallePedidoRepository.save(detalle);
    }// guardar

    // Actualizar un detalle existente
    public DetallePedido actualizar(long id, int cantidad,double precioUnidad ) {
        DetallePedido dp = null;
        if (detallePedidoRepository.existsById(id)) {
            DetallePedido de = detallePedidoRepository.findById(id).get();

            if (cantidad != 0) de.setCantidad(cantidad);
            if (precioUnidad != 0.0) de.setPrecioUnidad(precioUnidad);

            dp = detallePedidoRepository.save(de);
        }

        return dp;
    }

    // Eliminar un detalle por ID
    public DetallePedido eliminar(long id) {
DetallePedido dp =null;
if(detallePedidoRepository.existsById(id)){
dp=detallePedidoRepository.findById(id).get();
detallePedidoRepository.deleteById(id);
}


        return dp;
    }// eliminar

}// clase DetallePedidoServicio
