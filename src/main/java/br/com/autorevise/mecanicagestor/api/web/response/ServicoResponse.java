package br.com.autorevise.mecanicagestor.api.web.response;

public record ServicoResponse(
        String idServico,
        String descricao,
        Double valorServico
) {
}
