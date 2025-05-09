package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Especialidade;
import br.com.msoficinas.api.web.response.EspecialidadeResponse;

import java.util.List;

public interface EspecialidadeService {

    List<EspecialidadeResponse> carregarEspecialidades();

    Especialidade carregarEspecialidadePorId(String idEspecialidade) throws Exception;
}
