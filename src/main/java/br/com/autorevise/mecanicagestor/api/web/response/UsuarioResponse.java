package br.com.autorevise.mecanicagestor.api.web.response;

import br.com.autorevise.mecanicagestor.api.entities.Usuario;

public record UsuarioResponse (
        String token,
        Usuario usuario
){}
