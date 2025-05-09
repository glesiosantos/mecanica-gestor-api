package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarOrdemStatusRequest;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarStatusOficinaRequest;
import br.com.autorevise.mecanicagestor.api.web.request.OrdemRequest;
import br.com.autorevise.mecanicagestor.api.web.response.OrdemResponseList;

import java.util.List;

public interface OrdemService {

    Ordem registrarOrdemEstabelecimento(OrdemRequest request) throws Exception;

    List<OrdemResponseList> carregarOrdemEstabelecimento(String idEstabelecimento);

    void atualizarStatusOrdemEstabelecimento(AtualizarOrdemStatusRequest request) throws Exception;

    void atualizarStatusOficinaDoOrdemDoEstabelecimento(AtualizarStatusOficinaRequest request) throws Exception;

    Ordem carregarOrdemDoEstabelecimentoPeloSeuIdOrdem(String idOrdem, String idEstabelecimento) throws Exception;

    void atualizarOrdemTipoProposta(String idOrdem) throws Exception;
}
