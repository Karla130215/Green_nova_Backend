package greennova.backend.configuracion;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;
import jakarta.servlet.*;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JwtFilter extends GenericFilterBean {

    public static String signature = "DentroDeUn_UniversoConLasPosibilidades--C0moElNuestro-?>QuiEN!!IM4G1nAr_íaQu3EEsP0c0pr0BaBle-+-P0D3RV_-iSIT4RR)UNPL4net4";
    public static final String secret = Base64.getEncoder().encodeToString(signature.getBytes(StandardCharsets.UTF_8));

    public static SecretKey getSignInKey(){
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }//getSingKey

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        if (requiereAutenticacion(httpRequest)){
            String token = obtenerToken(httpRequest);
            validarToken(token);
        }//requiereAut
        filterChain.doFilter(servletRequest, servletResponse);
    }//doFilter

    private boolean requiereAutenticacion(HttpServletRequest httpRequest){
        String method = httpRequest.getMethod();
        String URI = httpRequest.getRequestURI();

        return (method.equals("POST") && !URI.contains("usuarios") ||
                method.equals("GET") && !URI.contains("productos") ||
                method.equals("PUT") ||
                method.equals("DELETE"));
    }//requiereAutenticacion

    private String obtenerToken(HttpServletRequest httpRequest) throws ServletException {
        String autHeader = httpRequest.getHeader("Authorization");

        if (autHeader == null || !autHeader.startsWith("Bearer ")){
            throw  new ServletException("Token invalido");
        }//if

        return  autHeader.substring(7);
    }//obtenerToken

    private void validarToken(String token) throws ServletException {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException e){
            throw new ServletException("2.- Token invalido, Signature expired");
        }//try-catch
    }//validarToken

}//class JwtFilter
