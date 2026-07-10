package greennova.backend.servicios;

import greennova.backend.modelos.Pedido;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServicio {

    private final ArrayList<Pedido> listaPedidos = new ArrayList<>();

    // Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        return listaPedidos;
    }// obtener todos

    // Obtener pedido por ID
    public Pedido obtenerPorId(long id) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getIdPedido() == id) {
                return pedido;
            }
        }
        return null;
    }// obtener por id

    // Guardar un nuevo pedido
    public Pedido guardar(Pedido pedido) {
        listaPedidos.add(pedido);
        return pedido;
    }// guardar

    // Actualizar un pedido existente
    public Pedido actualizar(long id, Pedido pedidoActualizado) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getIdPedido() == id) {
                pedidoActualizado.setIdPedido(id);
                listaPedidos.set(i, pedidoActualizado);
                return pedidoActualizado;
            }
        }
        return null;
    }// actualizar

    // Eliminar un pedido por ID
    public boolean eliminar(long id) {
        for (int i = 0; i < listaPedidos.size(); i++) {
            if (listaPedidos.get(i).getIdPedido() == id) {
                listaPedidos.remove(i);
                return true;
            }
        }
        return false;
    }// eliminar

}// clase PedidoServicio
