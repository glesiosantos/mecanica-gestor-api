package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record UsuarioComVariosEstabelecimentos(
        String token,
        List<EstabelecimentoResponse> estabelecimentos,
        String nome,
        String perfil
){}
