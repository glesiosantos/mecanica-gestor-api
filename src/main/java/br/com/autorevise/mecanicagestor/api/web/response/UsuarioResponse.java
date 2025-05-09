package br.com.autorevise.mecanicagestor.api.web.response;

import br.com.msoficinas.api.entidades.Usuario;

public record UsuarioResponse (
        String token,
        Usuario usuario
){}
