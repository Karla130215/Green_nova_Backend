package greennova.backend.controladores;

import greennova.backend.modelos.DetallePedido;
import greennova.backend.servicios.DetallePedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedidos/")
public class DetallePedidoControlador {

    @Autowired
    private DetallePedidoServicio detallePedidoServicio;

    @GetMapping
    public List<DetallePedido> listarDetalles() {
        return detallePedidoServicio.obtenerTodos();
    }


    @GetMapping(path = "{id}")
    public DetallePedido obtenerDetalle(@PathVariable long id) {
        return detallePedidoServicio.obtenerPorId(id);
    }

    @PostMapping
    public DetallePedido crearDetalle(@RequestBody DetallePedido detallePedido) {
        return detallePedidoServicio.guardar(detallePedido);
    }

    @PutMapping(path = "{id}")
    public DetallePedido actualizarDetalle(@PathVariable long id,
                                           @RequestParam(value ="cantidad", required = false, defaultValue = "0") int cantidad ,
                                           @RequestParam(value = "precioUnidad", required = false, defaultValue = "0") double precioUnidad) {

    return detallePedidoServicio.actualizar(id,cantidad,precioUnidad);
    }


    @DeleteMapping(path = "{id}")
    public DetallePedido eliminarDetalle(@PathVariable long id) {

        return detallePedidoServicio.eliminar(id);
    }
}