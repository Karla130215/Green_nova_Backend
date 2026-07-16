package greennova.backend.servicios;

import greennova.backend.modelos.Categoria;
import greennova.backend.modelos.Producto;
import greennova.backend.repositorios.CategoriaRepository;
import greennova.backend.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoServicio {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProductoServicio(ProductoRepository repository, CategoriaRepository categoriaRepository) {
        this.productoRepository = repository;
        this.categoriaRepository = categoriaRepository;
    }//constructor ProductoServicio

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }// obtener todos los productos

    public Producto getProducto(Long id) {
        return productoRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("Producto no encontrado con el id:" + id));
    }// obtener producto por id

    public Producto deleteProducto(Long id) {
        Producto producto = null;

        if (productoRepository.existsById(id)){
            producto = productoRepository.findById(id).get();
            productoRepository.deleteById(id);
        }//if
        return producto;
    }


    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, String nombre, String tipoProducto, Double precio, String imagen, String rLuz, String fRiego, String funcion, String descripcion, Long categoriaId) {
        Producto producto = null;
        if (productoRepository.existsById(id)) {
            Producto p = productoRepository.findById(id).get();

            if (nombre != null) p.setNombre(nombre);
            if (tipoProducto != null) p.setTipoProducto(tipoProducto);
            if (precio != null) p.setPrecio(precio);
            if (imagen != null) p.setImagen(imagen);
            if (rLuz != null) p.setrLuz(rLuz);
            if (fRiego != null) p.setfRiego(fRiego);
            if (funcion != null) p.setFuncion(funcion);
            if (descripcion != null) p.setDescripcion(descripcion);
            if(categoriaId != null) {
                if(categoriaRepository.existsById(categoriaId)) {
                    Categoria categoria = categoriaRepository.findById(categoriaId).get();
                    p.setCategoria(categoria);
                }//if
            }//ifCategoria
            producto = productoRepository.save(p);
        }//if
        return producto;
    }//actualizarProducto
}// clase ProductoServicio
