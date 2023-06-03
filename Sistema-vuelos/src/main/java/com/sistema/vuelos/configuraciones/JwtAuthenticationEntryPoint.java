package com.sistema.vuelos.configuraciones;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Método que se ejecuta cuando se produce una excepción de autenticación en el punto de entrada

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Usuario no autorizado");
        // Envía una respuesta de error con el código de estado HTTP 401 (Unauthorized) y el mensaje "Usuario no autorizado"
    }
}
