package greennova.backend.controladores;

import greennova.backend.modelos.Usuario;
import greennova.backend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioServicio.obtenerTodos();
    }

    @GetMapping(path = "{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return usuarioServicio.obtenerPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.guardar(usuario);
    }

    @PutMapping(path = "{id}")
    public Usuario actualizarUsuario(@PathVariable("usuarioId") long id,
                                     @RequestParam(value = "total")int total,
                                     @RequestParam(value = "nombre")String nombre,
                                     @RequestParam(value = "telefono")String telefono,
                                     @RequestParam("email")String email,
                                     @RequestParam("password")String password) {
        return usuarioServicio.actualizar(id,total,nombre,telefono,email,password);
    }

    @DeleteMapping(path = "{id}")
    public Usuario eliminarUsuario(@PathVariable long id) {
       return usuarioServicio.eliminar(id);
    }
}