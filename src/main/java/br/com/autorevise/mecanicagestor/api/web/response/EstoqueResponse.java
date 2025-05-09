package br.com.autorevise.mecanicagestor.api.web.response;

import java.time.LocalDate;

public record EstoqueResponse(
        String idEstoque,
        String idProduto,
        String fornecedor,
        int qtdAntes,
        int qtdAdquirida,
        Double precoCustoAdquirido,
        LocalDate dataRegistro
) {}
