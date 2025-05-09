package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.msoficinas.api.entidades.Cliente;
import br.com.msoficinas.api.enuns.TipoPessoa;
import br.com.msoficinas.api.web.request.ClienteAddRequest;
import br.com.msoficinas.api.web.response.ClienteResponse;
import br.com.msoficinas.api.web.response.FormClienteResponse;
import br.com.msoficinas.api.web.response.VeiculoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpfCnpj", source = "cpfOuCnpj")
    @Mapping(target = "razaoSocial", source = "razao")
    @Mapping(target = "nomeCompleto", source = "nome")
    @Mapping(target = "tipoPessoa", expression = "java(obterTipo(request))")
    @Mapping(target = "contatos", source = "contatos")
    Cliente converterRequestParaModel(ClienteAddRequest request);

    @Mapping(target = "idCliente", source = "id")
    @Mapping(target = "nome", source = "nomeCompleto")
    @Mapping(target = "razao", source = "razaoSocial")
    @Mapping(target = "cpfOuCnpj", source = "cpfCnpj")
    @Mapping(target = "tipo", expression = "java(obterTipoPessoaString(cliente))")
    @Mapping(target = "contatos", source = "contatos")
    @Mapping(target = "veiculos", expression = "java(obterVeiculosRelacionados(cliente))")
    ClienteResponse converterModelParaClienteResponse(Cliente cliente);

    @Mapping(target = "idCliente", source = "id")
    @Mapping(target = "nome", source = "nomeCompleto")
    @Mapping(target = "razao", source = "razaoSocial")
    @Mapping(target = "cpfOuCnpj", source = "cpfCnpj")
    @Mapping(target = "tipo", expression = "java(obterTipoPessoaString(cliente))")
    @Mapping(target = "contatos", source = "contatos")
    FormClienteResponse converterModelParaClienteFormResponse(Cliente cliente);

    default TipoPessoa obterTipo(ClienteAddRequest request) {
        return TipoPessoa.valueOf(request.tipoCliente());
    }

    default String obterTipoPessoaString(Cliente cliente) {
        return cliente.getTipoPessoa().getNome();
    }

    default List<VeiculoResponse> obterVeiculosRelacionados(Cliente cliente) {
        List<VeiculoResponse> veiculoResponses = new ArrayList<>();
        cliente.getVeiculos()
                .stream()
                .map(veiculo ->
                        veiculoResponses.add(new VeiculoResponse(veiculo.getId(), veiculo.getPlaca(), veiculo.getMarca().getNome(),veiculo.getModelo(), veiculo.getAno())))
                .collect(Collectors.toList());
        return veiculoResponses;
    }

}
