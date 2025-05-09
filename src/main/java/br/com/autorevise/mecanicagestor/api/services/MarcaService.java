package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Marca;

import java.util.List;

public interface MarcaService {

    List<Marca> carregarMarcas();

    Marca buscarMarcaPorId(String idMarca) throws Exception;
}
