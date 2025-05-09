package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.Produto;
import br.com.autorevise.mecanicagestor.api.enuns.CategoriaProduto;
import br.com.autorevise.mecanicagestor.api.web.request.ProdutoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.EstoqueResponse;
import br.com.autorevise.mecanicagestor.api.web.response.ProdutoListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProdutoMapper {

    @Mapping(target = "codigoProduto", source = "codigo")
    @Mapping(target = "referenciaProduto", source = "referencia")
    @Mapping(target = "descricaoProduto", source = "descricao")
    @Mapping(target = "categoriaProduto", expression = "java(converterCategoriaRequestParaEnum(request))")
    @Mapping(target = "quantidadeMinimaEstoque", source = "quantidadeMinimaEstoque")
    @Mapping(target = "quantidadeEstoque", source = "quantidadeEstoque")
    @Mapping(target = "precoCusto", source = "precoCusto")
    @Mapping(target = "percentualLucro", source = "percentualLucro")
    Produto converterRequestParaModelo(ProdutoRequest request);

    @Mapping(target = "idProduto", source = "id")
    @Mapping(target = "codigoProduto", source = "codigoProduto")
    @Mapping(target = "referencia", source = "referenciaProduto")
    @Mapping(target = "descricao", source = "descricaoProduto")
    @Mapping(target = "categoria", expression = "java(retornarCategoriaNome(produto))")
    @Mapping(target = "quantidadeMinimaEstoque", source = "quantidadeMinimaEstoque")
    @Mapping(target = "quantidadeEstoque", source = "quantidadeEstoque")
    @Mapping(target = "valorCusto", source = "precoCusto")
    @Mapping(target = "percentualLucro", source = "percentualLucro")
    @Mapping(target = "precoVenda", expression = "java(calcularPrecoDeVendas(produto))")
    @Mapping(target = "registros", expression = "java(carregarRegistros(produto))")
    ProdutoListResponse converterModeloParaResponseList(Produto produto);

    default CategoriaProduto converterCategoriaRequestParaEnum(ProdutoRequest request) {
        return CategoriaProduto.valueOf(request.categoria());
    }

    default String retornarCategoriaNome(Produto produto) {
        return produto.getCategoriaProduto().getTitulo();
    }

    default Double calcularPrecoDeVendas(Produto produto) {
        return produto.getPrecoCusto() * (1 + produto.getPercentualLucro()/100 );
    }

    default List<EstoqueResponse> carregarRegistros(Produto produto) {
        return produto.getRegistros().isEmpty() ? Collections.emptyList() :
                produto.getRegistros().stream()
                        .map(estoque -> new EstoqueResponse(estoque.getId(), estoque.getProduto().getId(), estoque.getFornecedor().getNomeFornecedor(), estoque.getQuantidadeAnterior(), estoque.getQuantidade(), estoque.getPrecoDeCusto(), estoque.getDataCriacao())).collect(Collectors.toList());
    }
}
