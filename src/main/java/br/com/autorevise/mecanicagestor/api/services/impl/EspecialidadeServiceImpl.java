package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Especialidade;
import br.com.autorevise.mecanicagestor.api.repositories.EspecialidadeRepository;
import br.com.autorevise.mecanicagestor.api.services.EspecialidadeService;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.autorevise.mecanicagestor.api.web.mappers.EspecialidadeMapper;
import br.com.autorevise.mecanicagestor.api.web.response.EspecialidadeResponse;
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
