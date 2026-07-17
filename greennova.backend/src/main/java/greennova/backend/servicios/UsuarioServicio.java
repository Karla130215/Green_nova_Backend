package greennova.backend.servicios;

import greennova.backend.modelos.Usuario;
import greennova.backend.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServicio {

   // private final ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private final UsuarioRepository usuarioRepository;

    public UsuarioServicio(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }// obtener todos

    // Obtener usuario por ID
    public Usuario obtenerPorId(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("El usuario con el id["+id+"] No existe"));

    }// obtener por id

    // Guardar un nuevo usuario
    public Usuario guardar(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }// guardar

    // Actualizar un usuario existente
    public Usuario actualizar(long id, int total,String nombre,String telefono,String email, String password) {
        Usuario usuario =null;
        if(usuarioRepository.existsById(id)){
            Usuario u =usuarioRepository.findById(id).get();
            if (nombre != null) u.setNombre(nombre);
            if(telefono!=null)u.setTelefono(telefono);
            if (email!=null)u.setEmail(email);
            if (password!=null)u.setPassword(password);
            usuarioRepository.save(u);

        }


        return null;
    }// actualizar

    // Eliminar un usuario por ID
    public Usuario eliminar(long id) {
        Usuario usuario=null;
        if(usuarioRepository.existsById(id)){
            usuario = usuarioRepository.findById(id).get();
            usuarioRepository.delete(usuario);


        }
return usuario;

    }// eliminar

}// clase UsuarioServicio
