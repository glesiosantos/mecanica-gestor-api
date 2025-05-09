package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.Especialidade;
import br.com.autorevise.mecanicagestor.api.web.response.EspecialidadeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EspecialidadeMapper {

    @Mapping(target = "idEspecialidade", source = "id")
    @Mapping(target = "nomeEspecialidade", source = "nome")
    @Mapping(target = "categoria", expression = "java(converterCategoria(especialidade))")
    @Mapping(target = "tipo", expression = "java(converterTipoVeiculo(especialidade))")
    EspecialidadeResponse converterModeloParaResponse(Especialidade especialidade);

    default String converterCategoria(Especialidade especialidade) {
        return especialidade.getCategoria().getTitulo();
    }

    default String converterTipoVeiculo(Especialidade especialidade) {
        return especialidade.getTipo().getTipo();
    }
}
