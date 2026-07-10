package greennova.backend.servicios;

import greennova.backend.modelos.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio {

    private final ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return listaUsuarios;
    }// obtener todos

    // Obtener usuario por ID
    public Usuario obtenerPorId(long id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }// obtener por id

    // Guardar un nuevo usuario
    public Usuario guardar(Usuario usuario) {
        listaUsuarios.add(usuario);
        return usuario;
    }// guardar

    // Actualizar un usuario existente
    public Usuario actualizar(long id, Usuario usuarioActualizado) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == id) {
                usuarioActualizado.setId(id);
                listaUsuarios.set(i, usuarioActualizado);
                return usuarioActualizado;
            }
        }
        return null;
    }// actualizar

    // Eliminar un usuario por ID
    public boolean eliminar(long id) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId() == id) {
                listaUsuarios.remove(i);
                return true;
            }
        }
        return false;
    }// eliminar

}// clase UsuarioServicio
