package br.com.autorevise.mecanicagestor.api.web.response;

import java.util.List;

public record OrdemDetailsResponse(
        String idCliente,
        String idEstabelecimento,
        String observacoes,
        int desconto,
        String formaPagamento,
        String cpfResponsavel,
        String tipoProposta,
        List<VeiculoFormResponse> veiculos,
        List<ItemOrdemResponse> produtos,
        List<ServicoResponse> servicos
) {}
