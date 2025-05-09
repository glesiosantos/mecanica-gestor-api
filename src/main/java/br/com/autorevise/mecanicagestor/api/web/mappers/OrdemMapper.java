package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;
import br.com.autorevise.mecanicagestor.api.entities.Veiculo;
import br.com.autorevise.mecanicagestor.api.enuns.OrdemStatus;
import br.com.autorevise.mecanicagestor.api.enuns.TipoDeProposta;
import br.com.autorevise.mecanicagestor.api.web.response.ItemOrdemResponse;
import br.com.autorevise.mecanicagestor.api.web.response.OrdemResponseList;
import br.com.autorevise.mecanicagestor.api.web.response.ServicoResponse;
import br.com.autorevise.mecanicagestor.api.web.response.VeiculoFormResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrdemMapper {

    @Mapping(target = "idOrdem", source = "id")
    @Mapping(target = "idEstabelecimento", source = "estabelecimento.id")
    @Mapping(target = "cpfCnpjCliente", source = "cliente.cpfCnpj")
    @Mapping(target = "idCliente", source = "cliente.id")
    @Mapping(target = "nomeCliente", source = "cliente.nomeCompleto")
    @Mapping(target = "valorEntrada", source = "valorEntrada")
    @Mapping(target = "parcelas", source = "totalParcelas")
    @Mapping(target = "statusPedido", expression = "java(obterStatusOrdem(ordem))")
    @Mapping(target = "statusOficina", expression = "java(obterStatusOficina(ordem))" )
    @Mapping(target = "observacoes", source = "observacoes")
    @Mapping(target = "tipoProposta", expression = "java(obterTipoProposta(ordem))")
    @Mapping(target = "formaPagamento", expression = "java(obterFormaDePagamento(ordem))")
    @Mapping(target = "produtos", expression = "java(obterItensDaOrdem(ordem))")
    @Mapping(target = "servicos", expression = "java(obterServicoDaOrdem(ordem))")
    @Mapping(target = "veiculo", expression = "java(obterVeiculoProposta(ordem))")
    @Mapping(target = "cpfResponsavel", source = "responsavel.cpf")
    @Mapping(target = "responsavel", source = "responsavel.nomeCompleto")
    @Mapping(target = "dtVencimentoOrcamento", expression = "java(calcularDataVencimentoOrcamento(ordem))")
    @Mapping(target = "dtCadastro", source = "dataCriacao")
    OrdemResponseList converterModelParaResponseList(Ordem ordem);

    default String obterTipoProposta(Ordem ordem) {
        return ordem.getTipoProposta().getDescricao();
    }

    default String obterFormaDePagamento(Ordem ordem) {
        return ordem.getFormaDePagamento().getDescricao();
    }

    default String obterStatusOrdem(Ordem ordem) {
        return ordem.getOrdemStatus().getDescricao();
    }

    default String obterStatusOficina(Ordem ordem) {
        return ordem.getOrdemStatus().equals(OrdemStatus.EE) ? ordem.getStatusOficina().getDescricao() : null;
    }

    default LocalDate calcularDataVencimentoOrcamento(Ordem ordem) {
        return ordem.getTipoProposta().equals(TipoDeProposta.O) ? ordem.getDataCriacao().plusDays(ordem.getTotalDiasProposta() + 1) : null;
    }

    default VeiculoFormResponse obterVeiculoProposta(Ordem ordem) {
        Veiculo veiculo = ordem.getVeiculo();
        if (veiculo == null) {
            return null;
        }

        return new VeiculoFormResponse(
                veiculo.getId(),
                veiculo.getPlaca(),
                null,
                veiculo.getMarca().getNome(),
                veiculo.getModelo(),
                veiculo.getAno()
        );
    }
    default List<ItemOrdemResponse> obterItensDaOrdem(Ordem ordem) {
        return Optional.ofNullable(ordem.getProdutos()).orElse(Collections.emptySet()).stream()
                .map(item -> new ItemOrdemResponse(item.getProduto().getId(), item.getProduto().getDescricaoProduto(), item.getQuantidade(), item.getProduto().getPrecoDeVenda()))
                .toList();
    }

    default List<ServicoResponse> obterServicoDaOrdem(Ordem ordem) {
        return Optional.ofNullable(ordem.getServicos()).orElse(Collections.emptySet()).stream()
                .map(servico -> new ServicoResponse(servico.getId(), servico.getDescricao(), servico.getValor())).toList();
    }
}