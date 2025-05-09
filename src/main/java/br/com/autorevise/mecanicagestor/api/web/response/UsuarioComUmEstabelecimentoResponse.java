package br.com.autorevise.mecanicagestor.api.web.response;

public record UsuarioComUmEstabelecimentoResponse(
        String token,
        String nome,
        boolean principal,
        String perfil,
        String cpf,
        PlanoResponse plano,
        EstabelecimentoResponse estabelecimento
) {}
