package greennova.backend.controladores;

import greennova.backend.modelos.Contacto;
import greennova.backend.servicios.ContactoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/contactos/")
public class ContactoControlador {

    private final  ContactoServicio contactoServicio;


    @Autowired
    public ContactoControlador(ContactoServicio contactoServicio) {
        this.contactoServicio = contactoServicio;
    }

    @GetMapping
    public List<Contacto> listarContactos() {
        return contactoServicio.obtenerTodos();
    }

    @GetMapping(path = "{contactoId}")
    public Contacto obtenerContacto(@PathVariable("contactoId") Long id) {

        return contactoServicio.obtenerPorId(id);
    }

    @PostMapping
    public Contacto crearContacto(@RequestBody Contacto contacto) {

        return contactoServicio.guardar(contacto);
    }

    @PutMapping(path = "{contactoId}")
    public Contacto actualizarContacto(@PathVariable("contactoId")Long id,
                                       @RequestParam(value="nombre", required = false) String nombre,
                                       @RequestParam(value="apellido", required = false) String apellido,
                                       @RequestParam(value="email", required = false) String email,
                                       @RequestParam(value="mensaje", required = false) String mensaje,
                                       @RequestParam(value="fechaEnvio",required = false) LocalDateTime fechaEnvio) {

        return contactoServicio.actualizar(id, nombre, apellido, email,mensaje,fechaEnvio);
    }

    @DeleteMapping(path = "{contactoId}")
    public Contacto eliminarContacto(@PathVariable("contactoId") long id) {
     return       contactoServicio.eliminar(id);

    }
}