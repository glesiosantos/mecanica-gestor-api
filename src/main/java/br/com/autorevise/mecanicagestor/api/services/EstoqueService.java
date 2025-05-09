package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.EstoqueProduto;
import br.com.autorevise.mecanicagestor.api.web.request.EstoqueRequest;
import br.com.autorevise.mecanicagestor.api.web.response.EstoqueResponse;

import java.util.List;

public interface EstoqueService {

    EstoqueProduto addEstoqueEmProduto(EstoqueRequest request) throws Exception;

    void atualizarHistoricoEstoque(EstoqueRequest request);

    List<EstoqueResponse> carregarHistoricoEstoqueProduto(String idProduto);
}
