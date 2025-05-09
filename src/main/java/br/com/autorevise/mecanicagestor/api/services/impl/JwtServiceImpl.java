package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.services.JwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.stream.Collectors;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${api.security.secret}")
    private String secret;

    @Override
    public String gerarToken(UserDetails userDetails) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            var scopes = userDetails
                    .getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));

            return JWT.create()
                    .withIssuer("ms-oficinas-api")
                    .withExpiresAt(criarDataExpiracao())
                    .withSubject(userDetails.getUsername())
                    .withClaim("scope", scopes)
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("*** "+ex);
        }
    }

    @Override
    public String validarToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).build().verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant criarDataExpiracao() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }
}
