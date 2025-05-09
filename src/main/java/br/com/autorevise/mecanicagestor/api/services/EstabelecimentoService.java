package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Estabelecimento;
import br.com.autorevise.mecanicagestor.api.web.request.EstabelecimentoRequest;
import br.com.autorevise.mecanicagestor.api.web.request.UpdatePlanoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ClienteResponse;
import br.com.autorevise.mecanicagestor.api.web.response.EstabelecimentoDadosResponse;

import java.util.Set;

public interface EstabelecimentoService {

    Estabelecimento cadastrarEstabelecimento(EstabelecimentoRequest request) throws Exception;

    Estabelecimento buscarEstabelecimentoPeloId(String idEstabelecimento) throws Exception;

    EstabelecimentoDadosResponse carregarDadosDoEstabelecimento(String idEstabelecimento) throws Exception;

    Set<ClienteResponse> carregarClientesDoEstabelecimento(String idEstabelecimento);

    ClienteResponse carregarClientePeloEstabelecimento(String idEstabelecimento, String idCliente) throws Exception;

    void updatePlanoEstabelecimento(UpdatePlanoRequest request) throws Exception;
}
