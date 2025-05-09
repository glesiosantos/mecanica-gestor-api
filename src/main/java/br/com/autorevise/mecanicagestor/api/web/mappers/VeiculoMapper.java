package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.msoficinas.api.entidades.Veiculo;
import br.com.msoficinas.api.web.request.VeiculoRequest;
import br.com.msoficinas.api.web.response.VeiculoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface VeiculoMapper {

    @Mapping(target = "id", source = "idVeiculo")
    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "modelo", source = "modelo")
    @Mapping(target = "marca", ignore = true)
    Veiculo converterRequestEmModel(VeiculoRequest request);

    @Mapping(target = "idVeiculo", source = "id")
    @Mapping(target = "placa", source = "placa")
    @Mapping(target = "modelo", source = "modelo")
    @Mapping(target = "marca", source = "marca.nome")
    @Mapping(target = "ano", source = "ano")
    VeiculoResponse converterModeloEmResponseList(Veiculo veiculo);
}
