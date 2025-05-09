package br.com.autorevise.mecanicagestor.api.web.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProdutoRequest(
        String idProduto,
        @NotBlank(message = "CÓDIGO é obrigatório") String codigo,
        @NotBlank(message = "REFERENCIA é obrigatório") String referencia,
        @NotBlank(message = "DESCRIÇÃO é obrigatório") String descricao,
        @NotNull(message = "CUSTO é obrigatório") Double precoCusto,
        @NotNull(message = "PERCENTUAL é obrigatório") Double percentualLucro,
        @NotBlank(message = "CATEGORIA é obrigatório") String categoria,
        @NotNull(message = "QUANTIDADE MINIMA é obrigatório") int quantidadeMinimaEstoque,
        @NotNull(message = "QUANTIDADE ATUAL é obrigatório") int quantidadeEstoque,
        @NotBlank(message = "Estabelecimento é obrigatório") String idEstabelecimento,
        List<String> modelos
) {}
