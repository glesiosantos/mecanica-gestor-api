package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.Estabelecimento;
import br.com.autorevise.mecanicagestor.api.web.request.EstabelecimentoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.EstabelecimentoDadosResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EstabelecimentoMapper {

    @Mapping(target = "clienteCodigoIntegracao", source = "idCliente")
    @Mapping(target = "cpfOuCnpj", source = "documento")
    @Mapping(target = "razaoSocial", source = "razao")
    @Mapping(target = "nomeFantasia", source = "fantasia")
    @Mapping(target = "plano", source = "plano")
    @Mapping(target = "dataFinalDoTeste", source = "dataVencimentoTeste")
    @Mapping(target = "diaVencimento", source = "diaVencimento")
    @Mapping(target = "endereco.cep", source = "cep")
    @Mapping(target = "endereco.logradouro", source = "logradouro")
    @Mapping(target = "endereco.bairro", source = "bairro")
    @Mapping(target = "endereco.cidade", source = "cidade")
    @Mapping(target = "endereco.estado", source = "estado")
    @Mapping(target = "endereco.latitude", source = "latitude")
    @Mapping(target = "endereco.longitude", source = "longitude")
    Estabelecimento converterRequestParaModel(EstabelecimentoRequest request);

    @Mapping(target = "idEstabelecimento", source = "id")
    @Mapping(target = "cpfCnpj", source = "cpfOuCnpj")
    @Mapping(target = "razao", source = "razaoSocial")
    @Mapping(target = "nomeFantasia", source = "nomeFantasia")
    @Mapping(target = "plano", source = "plano")
    @Mapping(target = "dataFinalTeste", source = "dataFinalDoTeste")
    @Mapping(target = "endereco.cep", source = "endereco.cep")
    @Mapping(target = "endereco.logradouro", source = "endereco.logradouro")
    @Mapping(target = "endereco.bairro", source = "endereco.bairro")
    @Mapping(target = "endereco.cidade", source = "endereco.cidade")
    @Mapping(target = "endereco.estado", source = "endereco.estado")
    @Mapping(target = "contatos", source = "contatos")
    EstabelecimentoDadosResponse converterModelEmResponse(Estabelecimento estabelecimento);

    default String converterPlano(Estabelecimento estabelecimento) {
        return estabelecimento.getPlano().getDescricao();
    }
}
