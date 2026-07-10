package greennova.backend.controladores;

import greennova.backend.modelos.Pedido;
import greennova.backend.servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedido(@PathVariable long id) {
        return pedidoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoServicio.guardar(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable long id, @RequestBody Pedido pedido) {
        return pedidoServicio.actualizar(id, pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable long id) {
        if (pedidoServicio.eliminar(id)) {
            return "Pedido eliminado con éxito.";
        }
        return "Pedido no encontrado.";
    }
}