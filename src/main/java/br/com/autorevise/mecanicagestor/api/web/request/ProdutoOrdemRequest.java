package br.com.autorevise.mecanicagestor.api.web.request;

public record ProdutoOrdemRequest(
    String idProduto,
    int quantidade
) {}
