package greennova.backend.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                // 1. Agregamos esta sección para permitir el CORS
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOriginPatterns(List.of("*")); // Permite que tu frontend se conecte
                    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Permite los métodos necesarios
                    configuration.setAllowedHeaders(List.of("*")); // Permite cualquier tipo de encabezado
                    return configuration;
                }))
                // 2. Tu configuración original de CSRF se mantiene igual
                .csrf((csrf) -> csrf.disable());

        return http.build();//termina de leer cadena de configuracion
    }//config

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }//passwordEncoder
}//secConfig