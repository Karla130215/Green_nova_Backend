package greennova.backend.servicios;

import greennova.backend.modelos.Categoria;
import greennova.backend.repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository repository) {
        this.categoriaRepository = repository;
    }//constructor CategoriaService

    public List<Categoria> getCategoria() {
        return categoriaRepository.findAll();
    }//getCategorias

    public Categoria getCategoria(Long id) {
        return categoriaRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("No existe la categoria con el id: " + id));
    }//getCategoria

    public Categoria deleteCategoria(Long id) {
        Categoria categoria = null; //crear con valor nulo

        if (categoriaRepository.existsById(id)){ //verifica si existe el id
            categoria = categoriaRepository.findById(id).get();
            categoriaRepository.deleteById(id); //elimina la categoria
        }//if
        return categoria;
    }//deleteCategoria


    public Categoria crearCategoria(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNombre(categoria.getNombre());

        if (cat.isEmpty()){
            categoriaRepository.save(categoria);
        } else {
            categoria = null;
        }//if

        return categoria;
    }//crearCategoria

    public Categoria actualizarCategoria(Long id, String nombre, String descripcion) {
        Categoria categoria = null;

        if (categoriaRepository.existsById(id)) {
            Categoria cat = categoriaRepository.findById(id).get();//nuevo objeto

            if (nombre != null) cat.setNombre(nombre);
            if (descripcion != null) cat.setDescripcion(descripcion);

            categoria = categoriaRepository.save(cat);//guarda el valor
        }//if
        return categoria;
    }//actualizarCategoria
}//class Categoria
