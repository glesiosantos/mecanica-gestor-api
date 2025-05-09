package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.ServicoEstabelecimento;
import br.com.autorevise.mecanicagestor.api.web.response.ServicoEstabelecimentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ServicoEstabelecimentoMapper {

    @Mapping(target = "idServico", source = "id")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "valor", source = "valor")
    ServicoEstabelecimentoResponse converterModeloEmResponse(ServicoEstabelecimento servico);
}
