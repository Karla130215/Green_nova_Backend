package greennova.backend.servicios;

import io.jsonwebtoken.Jwts;
import greennova.backend.configuracion.JwtFilter;
import greennova.backend.dto.LoginRequest;
import greennova.backend.dto.PassDto;
import greennova.backend.modelos.Usuario;
import greennova.backend.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioServicio {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public UsuarioServicio(UsuarioRepository repository) {
        this.usuarioRepository = repository;
    }//constructor UsuarioServicio

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }//getUsuario

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).
                orElseThrow(()-> new IllegalArgumentException("No existe el usuario con el id: " + id));
    }//getUsuario id

    public Usuario deleteUsuario(Long id) {
        Usuario usuario = null;

        if (usuarioRepository.existsById(id)) {
            usuario = usuarioRepository.findById(id).get();
            usuarioRepository.deleteById(id);
        }//if
        return  usuario;
    }//deleteUsuario


    public Usuario crearUsuario(Usuario usuario) {
        Optional<Usuario> usr = usuarioRepository.findByEmail(usuario.getEmail());

        if (usr.isEmpty()){
            usuario.setPass(encoder.encode(usuario.getPass()));
            usuarioRepository.save(usuario);
        } else {
            usuario = null;
        }
        return usuario;
    }//crearUsuario


    public Usuario actualizarUsuario(Long id, PassDto dto) {
        Usuario usuario = null;

        if (usuarioRepository.existsById(id)) {
            Usuario u = usuarioRepository.findById(id).get();

            if (encoder.matches(dto.getPassActual(), u.getPass())) {
                u.setPass(encoder.encode(dto.getPassNuevo()));
                usuario = usuarioRepository.save(u);
            }//if pass
        }//if

        return usuario;
    }//actualizarUsuario

    public boolean validarUsuario(LoginRequest request){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(request.getEmail());

        if (usuario.isPresent()){
            Usuario u = usuario.get();

            if (encoder.matches(request.getPass(), u.getPass())){

            }//request pass matches
        }//isPresent

        return false;
    }//validarUsuario

    public String generarToken(String email){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 3);

        return Jwts.builder()
                .setSubject(email)
                .claim("role", "user")
                .issuedAt(new Date())
                .expiration(calendar.getTime())
                .signWith(JwtFilter.getSignInKey())
                .compact();
    }//generarToken
}// clase UsuarioServicio
