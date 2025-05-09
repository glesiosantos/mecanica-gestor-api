package br.com.autorevise.mecanicagestor.api.web.response;

public record OrdemStatusResponse(
        String sigla,
        String descricao,
        String texto,
        String para
) {
}
