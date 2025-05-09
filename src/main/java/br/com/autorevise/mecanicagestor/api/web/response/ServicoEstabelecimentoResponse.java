package br.com.autorevise.mecanicagestor.api.web.response;

public record ServicoEstabelecimentoResponse(
        String idServico,
        Double valor,
        String descricao
) {}
