package greennova.backend.servicios;

import greennova.backend.modelos.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServicio {

    private final ArrayList<Producto> listaProductos = new ArrayList<>();

    // Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return listaProductos;
    }// obtener todos

    // Obtener producto por ID
    public Producto obtenerPorId(long id) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null;
    }// obtener por id

    // Guardar un nuevo producto
    public Producto guardar(Producto producto) {
        listaProductos.add(producto);
        return producto;
    }// guardar

    // Actualizar un producto existente
    public Producto actualizar(long id, Producto productoActualizado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                productoActualizado.setId(id);
                listaProductos.set(i, productoActualizado);
                return productoActualizado;
            }
        }
        return null;
    }// actualizar

    // Eliminar un producto por ID
    public boolean eliminar(long id) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                listaProductos.remove(i);
                return true;
            }
        }
        return false;
    }// eliminar

}// clase ProductoServicio
