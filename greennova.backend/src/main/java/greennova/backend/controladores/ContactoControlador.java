package greennova.backend.controladores;

import greennova.backend.modelos.Contacto;
import greennova.backend.servicios.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contactos")
public class ContactoControlador {

    @Autowired
    private ContactoServicio contactoServicio;

    @GetMapping
    public List<Contacto> listarContactos() {
        return contactoServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Contacto obtenerContacto(@PathVariable long id) {
        return contactoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Contacto crearContacto(@RequestBody Contacto contacto) {
        return contactoServicio.guardar(contacto);
    }

    @PutMapping("/{id}")
    public Contacto actualizarContacto(@PathVariable long id, @RequestBody Contacto contacto) {
        return contactoServicio.actualizar(id, contacto);
    }

    @DeleteMapping("/{id}")
    public String eliminarContacto(@PathVariable long id) {
        if (contactoServicio.eliminar(id)) {
            return "Mensaje de contacto eliminado.";
        }
        return "Contacto no encontrado.";
    }
}