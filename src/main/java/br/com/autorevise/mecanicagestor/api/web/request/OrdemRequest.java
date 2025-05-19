package br.com.autorevise.mecanicagestor.api.web.request;

import br.com.autorevise.mecanicagestor.api.web.response.ServicoResponse;

import java.util.List;

public record OrdemRequest (
        String idOrdem,
        String idEstabelecimento,
        String idCliente,
        VeiculoRequest veiculo,
        Double desconto,
        String statusPedido,
        String observacoes,
        String formaPagamento,
        String tipoProposta,
        int diasValidade,
        Double valorEntrada,
        int parcelas,
        List<ProdutoOrdemRequest> produtos,
        List<ServicoResponse> servicos,
        String cpfResponsavel
){}
