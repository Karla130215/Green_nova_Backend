package greennova.backend.servicios;

import greennova.backend.modelos.Contacto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactoServicio {

    private final ArrayList<Contacto> listaContactos = new ArrayList<>();

    // Obtener todos los contactos
    public List<Contacto> obtenerTodos() {
        return listaContactos;
    }// obtener todos

    // Obtener contacto por ID
    public Contacto obtenerPorId(long id) {
        for (Contacto contacto : listaContactos) {
            if (contacto.getIdMensaje() == id) {
                return contacto;
            }
        }
        return null;
    }// obtener por id

    // Guardar un nuevo contacto
    public Contacto guardar(Contacto contacto) {
        listaContactos.add(contacto);
        return contacto;
    }// guardar

    // Actualizar un contacto existente
    public Contacto actualizar(long id, Contacto contactoActualizado) {
        for (int i = 0; i < listaContactos.size(); i++) {
            if (listaContactos.get(i).getIdMensaje() == id) {
                contactoActualizado.setIdMensaje(id);
                listaContactos.set(i, contactoActualizado);
                return contactoActualizado;
            }
        }
        return null;
    }// actualizar

    // Eliminar un contacto por ID
    public boolean eliminar(long id) {
        for (int i = 0; i < listaContactos.size(); i++) {
            if (listaContactos.get(i).getIdMensaje() == id) {
                listaContactos.remove(i);
                return true;
            }
        }
        return false;
    }// eliminar

}// clase ContactoServicio
