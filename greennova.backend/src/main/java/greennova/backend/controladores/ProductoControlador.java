package greennova.backend.controladores;

import greennova.backend.modelos.Producto;
import greennova.backend.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listarProductos() {
        return productoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable long id) {
        return productoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServicio.guardar(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable long id, @RequestBody Producto producto) {
        return productoServicio.actualizar(id, producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable long id) {
        if (productoServicio.eliminar(id)) {
            return "Producto eliminado con éxito.";
        }
        return "Producto no encontrado.";
    }
}