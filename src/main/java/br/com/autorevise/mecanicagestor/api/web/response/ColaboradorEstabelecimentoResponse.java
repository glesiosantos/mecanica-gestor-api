package br.com.autorevise.mecanicagestor.api.web.response;

public record ColaboradorEstabelecimentoResponse(
        String idColaborador,
        String idEstabelecimento,
        String cpf,
        String nomeCompleto,
        String perfil,
        boolean usuarioPrincipal,
        String criadoEm
) {
}
