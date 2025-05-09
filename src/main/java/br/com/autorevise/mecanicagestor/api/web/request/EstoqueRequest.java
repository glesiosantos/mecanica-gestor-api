package br.com.autorevise.mecanicagestor.api.web.request;

public record EstoqueRequest(
        String idProduto,
        String idFornecedor,
        String idEstabelecimento,
        int quantidade,
        Double precoCusto
) {}
