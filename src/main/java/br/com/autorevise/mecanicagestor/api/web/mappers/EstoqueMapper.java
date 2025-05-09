package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.EstoqueProduto;
import br.com.autorevise.mecanicagestor.api.web.response.EstoqueResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstoqueMapper {

    @Mapping(target = "idEstoque", source = "id")
    @Mapping(target = "precoCustoAdquirido", source = "precoDeCusto")
    @Mapping(target = "qtdAdquirida", source = "quantidade")
    @Mapping(target = "qtdAntes", source = "quantidadeAnterior")
    @Mapping(target = "fornecedor", expression = "java(converterFornecedor(estoque))")
    @Mapping(target = "dataRegistro", source = "dataCriacao")
    EstoqueResponse converterModeloEmResponse(EstoqueProduto estoque);

    default String converterFornecedor(EstoqueProduto estoqueProduto) {
        return estoqueProduto.getFornecedor().getNomeFornecedor();
    }
}
