package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Marca;
import br.com.autorevise.mecanicagestor.api.repositories.MarcaRepository;
import br.com.autorevise.mecanicagestor.api.services.MarcaService;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Marca> carregarMarcas() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca buscarMarcaPorId(String idMarca) throws Exception {
        return marcaRepository.findById(idMarca)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhuma marca encontrada com este id "+idMarca));
    }

}
