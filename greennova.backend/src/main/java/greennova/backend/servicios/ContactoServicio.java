package greennova.backend.servicios;

import greennova.backend.modelos.Contacto;
import greennova.backend.modelos.Producto;
import greennova.backend.repositorios.ContactoRepository;
import greennova.backend.repositorios.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoServicio {

    private final ContactoRepository contactoRepository;
   // private final ArrayList<Contacto> listaContactos = new ArrayList<>();


    public ContactoServicio(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    // Obtener todos los contactos
    public List<Contacto> obtenerTodos() {
        return contactoRepository.findAll();
    }// obtener todos

    // Obtener contacto por ID
    public Contacto obtenerPorId(long id) {
        return contactoRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("El contacto con el ID [" +id+ "] no existe"));

    }// obtener por id

    // Eliminar un contacto por ID
    public Contacto eliminar(Long id) {
        Contacto contacto = null;

        if(contactoRepository.existsById(id)){
            contacto = contactoRepository.findById(id).get();
            contactoRepository.deleteById(id);
        }

        return contacto;
    }// eliminar

    // Guardar un nuevo contacto
    public Contacto guardar(Contacto contacto) {
        contacto.setFechaEnvio(LocalDateTime.now());
        return contactoRepository.save(contacto);
    }// guardar

    // Actualizar un contacto existente
    public Contacto actualizar(Long id, String nombre, String apellido, String email, String mensaje, LocalDateTime fechaEnvio) {

        Contacto contacto = null;

        if(contactoRepository.existsById(id)){
            Contacto cont = contactoRepository.findById(id).get();

            if(nombre != null) cont.setNombre(nombre);
            if(apellido != null) cont.setApellido(apellido);
            if(email != null) cont.setEmail(email);
            if(mensaje != null) cont.setMensaje(mensaje);
            if(fechaEnvio != null) cont.setFechaEnvio(fechaEnvio);


            contacto = contactoRepository.save(cont);

        }

        return contacto;
    }// actualizar



}// clase ContactoServicio
