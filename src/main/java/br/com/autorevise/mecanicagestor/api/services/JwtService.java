package br.com.autorevise.mecanicagestor.api.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String gerarToken(UserDetails userDetails);

    String validarToken(String token);
}
