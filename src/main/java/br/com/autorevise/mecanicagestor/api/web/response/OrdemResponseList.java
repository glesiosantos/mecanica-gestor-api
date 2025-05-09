package br.com.autorevise.mecanicagestor.api.web.response;

import java.time.LocalDate;
import java.util.List;

public record OrdemResponseList(
    String idOrdem,
    String idEstabelecimento,
    Double valorEntrada,
    int parcelas,
    int diasValidade,
    Double desconto,
    String formaPagamento,
    String tipoProposta,
    LocalDate dtVencimentoOrcamento,
    String statusPedido,
    String statusOficina,
    String idCliente,
    String nomeCliente,
    String cpfCnpjCliente,
    String cpfResponsavel,
    String responsavel,
    String observacoes,
    LocalDate dtCadastro,
    VeiculoFormResponse veiculo,
    List<ItemOrdemResponse> produtos,
    List<ServicoResponse> servicos
) {}
