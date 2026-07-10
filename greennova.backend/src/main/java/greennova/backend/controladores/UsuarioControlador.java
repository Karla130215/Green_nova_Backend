package greennova.backend.controladores;

import greennova.backend.modelos.Usuario;
import greennova.backend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServicio.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return usuarioServicio.obtenerPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable long id, @RequestBody Usuario usuario) {
        return usuarioServicio.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public String eliminarUsuario(@PathVariable long id) {
        if (usuarioServicio.eliminar(id)) {
            return "Usuario eliminado con éxito.";
        }
        return "Usuario no encontrado.";
    }
}