package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record ProdutoListResponse(
        String idProduto,
        String codigoProduto,
        String referencia,
        String descricao,
        Double valorCusto,
        Double percentualLucro,
        String categoria,
        int quantidadeMinimaEstoque,
        int quantidadeEstoque,
        Double precoVenda,
        FornecedorResponse fornecedor,
        List<ModeloCompativeisResponse> modelos,
        List<EstoqueResponse> registros
) {}
