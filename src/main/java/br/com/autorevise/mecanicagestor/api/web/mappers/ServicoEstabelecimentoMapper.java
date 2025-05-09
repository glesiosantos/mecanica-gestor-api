package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.msoficinas.api.entidades.ServicoEstabelecimento;
import br.com.msoficinas.api.enuns.CategoriaEspecialidade;
import br.com.msoficinas.api.enuns.TipoPessoa;
import br.com.msoficinas.api.web.request.ClienteAddRequest;
import br.com.msoficinas.api.web.response.ServicoEstabelecimentoResponse;
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
