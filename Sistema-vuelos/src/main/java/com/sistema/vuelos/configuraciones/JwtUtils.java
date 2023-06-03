package com.sistema.vuelos.configuraciones;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private String SECRET_KEY = "examportal";

    public String extractUsername(String token) {
        // Extrae el nombre de usuario del token
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        // Extrae la fecha de expiración del token
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        // Extrae una reclamación específica del token utilizando una función de resolución de reclamaciones
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Extrae todas las reclamaciones del token utilizando la clave secreta
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        // Verifica si el token ha expirado comparando la fecha de expiración con la fecha actual
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        // Genera un nuevo token JWT utilizando los detalles del usuario
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        // Crea un nuevo token JWT con las reclamaciones, el sujeto (nombre de usuario), la fecha de emisión y la fecha de expiración
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        // Valida el token verificando que el nombre de usuario en el token coincida con el nombre de usuario en los detalles del usuario y que el token no haya expirado
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
