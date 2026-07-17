package greennova.backend.controladores;

import greennova.backend.modelos.Producto;
import greennova.backend.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    @Autowired
    public ProductoControlador(ProductoServicio servicio){
        this.productoServicio = servicio;
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoServicio.getProductos();
    }//getProductos obtiene la lista de todos los productos desde ProductoServicio

    @GetMapping("{productoId}")
    public Producto getProducto(@PathVariable("productoId") Long id) {
        return productoServicio.getProducto(id);
    }//getProdcuto obtiene el producto por ID

    @DeleteMapping("{productoId}")
    public Producto deleteProducto(@PathVariable("productoId") Long id){
        return productoServicio.deleteProducto(id);
    }//deleteProdcuto Elimina el producto por id

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServicio.crearProducto(producto);
    }//crearProducto Crea un nuevo producto

    @PutMapping("{productoId}")
    public Producto actualizarProducto(@PathVariable("productoId") Long id,
                                       @RequestParam(value = "nombre", required = false) String nombre,
                                       @RequestParam(value = "tipoProducto", required = false) String tipoProducto,
                                       @RequestParam(value = "precio", required = false)Double precio,
                                       @RequestParam(value = "imagen", required = false)String imagen,
                                       @RequestParam(value = "rLuz", required = false)String rLuz,
                                       @RequestParam(value = "fRiego", required = false)String fRiego,
                                       @RequestParam(value = "funcion", required = false)String funcion,
                                       @RequestParam(value = "descripcion", required = false)String descripcion,
                                       @RequestParam(value = "categoriaId", required = false)Long categoriaId) {
        return productoServicio.actualizarProducto(id, nombre, tipoProducto, precio, imagen, rLuz, fRiego, funcion, descripcion, categoriaId);
    }//actualizaProducto

}//class ProductoControlador