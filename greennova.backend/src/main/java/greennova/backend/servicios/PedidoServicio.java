package greennova.backend.servicios;

import greennova.backend.modelos.Pedido;
import greennova.backend.repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServicio {

    // private final ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServicio(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {

        return pedidoRepository.findAll();
    }// obtener todos

    // Obtener pedido por ID
    public Pedido obtenerPorId(Long id) {

        return pedidoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("El pedido por el id [" + id + "] no existe"));
    }// obtener por id

    // Eliminar un pedido por ID
    public Pedido eliminar(Long id) {
        Pedido pedido = null;
        if (pedidoRepository.existsById(id)) {
            pedido = pedidoRepository.findById(id).get();
            pedidoRepository.deleteById(id);
        }
        return pedido;

    }// eliminar

    // Crear un nuevo pedido
    public Pedido crearPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }// crear

    // Actualizar un pedido existente
    public Pedido actualizar(long id, String fechaPedido, Double total, String estado) {
        Pedido pedido = null;
        if (pedidoRepository.existsById(id)) {
            Pedido p = pedidoRepository.findById(id).get();
            if (fechaPedido != null) p.setFechaPedido(fechaPedido);
            if (total != null) p.setTotal(total);
            if (estado != null) p.setEstado(estado);
            pedido = pedidoRepository.save(p);

        }
        return pedido;
    }// actualizar


}// clase PedidoServicio
