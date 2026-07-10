package greennova.backend.controladores;

import greennova.backend.modelos.DetallePedido;
import greennova.backend.servicios.DetallePedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedidos")
public class DetallePedidoControlador {

    @Autowired
    private DetallePedidoServicio detallePedidoServicio;

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoServicio.obtenerTodos();
    }


    @GetMapping("/{id}")
    public DetallePedido obtenerDetalle(@PathVariable long id) {
        return detallePedidoServicio.obtenerPorId(id);
    }

    @PostMapping
    public DetallePedido crearDetalle(@RequestBody DetallePedido detallePedido) {
        return detallePedidoServicio.guardar(detallePedido);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizarDetalle(@PathVariable long id, @RequestBody DetallePedido detallePedido) {
        return detallePedidoServicio.actualizar(id, detallePedido);
    }

    @DeleteMapping("/{id}")
    public String eliminarDetalle(@PathVariable long id) {
        if (detallePedidoServicio.eliminar(id)) {
            return "Detalle de pedido eliminado con éxito.";
        }
        return "Detalle no encontrado.";
    }
}