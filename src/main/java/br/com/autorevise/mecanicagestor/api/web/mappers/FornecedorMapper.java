package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.Fornecedor;
import br.com.autorevise.mecanicagestor.api.web.request.FornecedorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FornecedorMapper {

    @Mapping(target = "id", source = "idFornecedor")
    @Mapping(target = "nomeFornecedor", source = "nomeFornecedor")
    @Mapping(target = "contatos", source = "contatos")
    Fornecedor converterRequestParaModelo(FornecedorRequest request);
}
