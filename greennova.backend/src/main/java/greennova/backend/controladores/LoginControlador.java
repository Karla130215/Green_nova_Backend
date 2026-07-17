package greennova.backend.controladores;

import greennova.backend.dto.LoginRequest;
import greennova.backend.dto.TokenAcceso;
import greennova.backend.servicios.UsuarioServicio;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginControlador {

    private final UsuarioServicio usuarioServicio;

    @Autowired
    public LoginControlador(UsuarioServicio servicio) {
        this.usuarioServicio = servicio;
    }//constructor

    @PostMapping
    public TokenAcceso validarUsuario(@RequestBody LoginRequest request) throws ServletException {
        if (usuarioServicio.validarUsuario(request)){
            return new TokenAcceso(usuarioServicio.generarToken(request.getEmail()));
        }//if

        throw  new ServletException("Login error: El correo o la contraseña son incorrectos");
    }//validarUsuario
}//class LoginControlador
