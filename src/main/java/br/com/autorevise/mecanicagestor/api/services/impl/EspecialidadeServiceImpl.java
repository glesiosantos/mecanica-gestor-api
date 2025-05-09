package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.entidades.Especialidade;
import br.com.msoficinas.api.repositories.EspecialidadeRepository;
import br.com.msoficinas.api.services.EspecialidadeService;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.mappers.EspecialidadeMapper;
import br.com.msoficinas.api.web.response.EspecialidadeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EspecialidadeServiceImpl implements EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private EspecialidadeMapper especialidadeMapper;

    @Transactional(readOnly = true)
    @Override
    public List<EspecialidadeResponse> carregarEspecialidades() {
        return (List<EspecialidadeResponse>) especialidadeRepository.findAll().stream()
                .map(especialidade -> especialidadeMapper.converterModeloParaResponse(especialidade))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Especialidade carregarEspecialidadePorId(String idEspecialidade) throws Exception {
        return especialidadeRepository.findById(idEspecialidade)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhuma especialidade encontrada com este id "+idEspecialidade));
    }
}
