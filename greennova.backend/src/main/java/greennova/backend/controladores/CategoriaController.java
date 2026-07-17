package greennova.backend.controladores;

import greennova.backend.modelos.Categoria;
import greennova.backend.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")// http://localhost:8080/api/categorias/
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }//constructor CategoriaController

    @GetMapping // http://localhost:8080/api/categorias
    public List<Categoria> getCategorias(){
        return categoriaService.getCategoria();
    }//getCategorias Obtiene la informacion

    @GetMapping(path="{categoriaId}")// http://localhost:8080/api/categorias/{categoriaId}
    public Categoria getCategoria(@PathVariable("categoriaId") Long id){
        return categoriaService.getCategoria(id);
    }//getCategoria Obtiene la informacion de la categoria si el ID coincide

    @DeleteMapping(path="{categoriaId}")
    public Categoria deleteCategoria(@PathVariable("categoriaId") Long id){
        return categoriaService.deleteCategoria(id);
    }//deleteCategoria

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria){
        return categoriaService.crearCategoria(categoria);
    }//crearCategoria

    @PutMapping(path = "{categoriaId}")//localhost:8080/api/categorias/{categoriaId}
    public Categoria actualizarCategoria(@PathVariable("categoriaId") Long id,
                                         @RequestParam(value = "nombre", required = false) String nombre,
                                         @RequestParam(value = "descripcion", required = false) String descripcion){
        return categoriaService.actualizarCategoria(id, nombre, descripcion);
    }//actualizarCategoria

}//clase CategoriaController
