package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Especialidade;
import br.com.autorevise.mecanicagestor.api.web.response.EspecialidadeResponse;

import java.util.List;

public interface EspecialidadeService {

    List<EspecialidadeResponse> carregarEspecialidades();

    Especialidade carregarEspecialidadePorId(String idEspecialidade) throws Exception;
}
