package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.EstoqueProduto;
import br.com.msoficinas.api.web.request.EstoqueRequest;
import br.com.msoficinas.api.web.response.EstoqueResponse;

import java.util.List;

public interface EstoqueService {

    EstoqueProduto addEstoqueEmProduto(EstoqueRequest request) throws Exception;

    void atualizarHistoricoEstoque(EstoqueRequest request);

    List<EstoqueResponse> carregarHistoricoEstoqueProduto(String idProduto);
}
