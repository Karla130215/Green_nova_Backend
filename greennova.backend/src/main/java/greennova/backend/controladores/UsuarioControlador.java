package greennova.backend.controladores;

import greennova.backend.dto.PassDto;
import greennova.backend.modelos.Usuario;
import greennova.backend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioControlador {


    private UsuarioServicio usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicio servicio) {
        this.usuarioServicio = servicio;
    }//constructor UsuarioControlador

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioServicio.getUsuarios();
    }//getUsuarios

    @GetMapping("{usuarioId}")
    public Usuario getUsuario(@PathVariable("usuarioId") Long id) {
        return usuarioServicio.getUsuario(id);
    }//getUsuarioId

    @DeleteMapping(path = "{usuarioId}")
    public Usuario deleteUsuario(@PathVariable("usuarioId") Long id){
        return usuarioServicio.deleteUsuario(id);
    }//deleteUsuario Elimina usuario por Id coincidente

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.crearUsuario(usuario);
    }//crearUsuario

    @PutMapping(path = "{usuarioId}")
    public Usuario actualizarUsuario(@PathVariable("usuarioId") Long id,
                                     @RequestBody PassDto dto) {
        return usuarioServicio.actualizarUsuario(id, dto);
    }//actualizarUsuario
}//class UsuarioControlador
