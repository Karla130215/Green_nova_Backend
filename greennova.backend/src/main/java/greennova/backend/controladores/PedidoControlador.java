package greennova.backend.controladores;

import greennova.backend.modelos.Pedido;
import greennova.backend.servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/pedidos/") // http://localhost:8080/api/pedidos/
public class PedidoControlador {
    private final PedidoServicio pedidoService;//se inyecta dependencia con @Autowired
    @Autowired
    public PedidoControlador(PedidoServicio pedidoService) {
        this.pedidoService = pedidoService;
    }

//Se hace CRUD
    @GetMapping // http://localhost:8080/api/pedidos/
    public List<Pedido> listarPedidos() {

        return pedidoService.obtenerTodos();
    }

    @GetMapping(path = "{id}") //localhost:8080/api/pedidos/id
    public Pedido obtenerPedido(@PathVariable long id) {
        return pedidoService.obtenerPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

    @PutMapping(path = "{id}")
    public Pedido actualizarPedido(@PathVariable("id")Long id,
                                   @RequestParam(value="fechaPedido", required = false) String fechaPedido,
                                   @RequestParam(value="total", required = false) Double total,
                                   @RequestParam(value="estado", required = false) String estado) {
        return pedidoService.actualizar(id,fechaPedido,total,estado);
    }

    @DeleteMapping(path ="{id}")
    public Pedido eliminarPedido(@PathVariable Long id) {
       return pedidoService.eliminar(id);
    }
}