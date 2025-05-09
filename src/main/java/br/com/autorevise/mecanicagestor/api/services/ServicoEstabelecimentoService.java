package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.ServicoEstabelecimento;
import br.com.msoficinas.api.web.request.ServicoRequest;
import br.com.msoficinas.api.web.response.ServicoEstabelecimentoResponse;

import java.util.List;

public interface ServicoEstabelecimentoService {

    ServicoEstabelecimento addServico(ServicoRequest request) throws Exception;

    void removerServidoDoEstabelecimento(String idEstabelecimento, String idServico) throws Exception;

    void atualizarServidoDoEstabelecimento(ServicoRequest request) throws Exception;

    List<ServicoEstabelecimentoResponse> carregarServicosDoEstabelecimento(String id);
}
