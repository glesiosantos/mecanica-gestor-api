package br.com.autorevise.mecanicagestor.api.web.response;

public record ItemOrdemResponse(
        String idProduto,
        String descricao,
        int quantidade,
        Double precoUnitario
) {}
