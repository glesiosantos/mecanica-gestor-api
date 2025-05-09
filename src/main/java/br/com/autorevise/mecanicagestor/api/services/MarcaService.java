package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Marca;

import java.util.List;

public interface MarcaService {

    List<Marca> carregarMarcas();

    Marca buscarMarcaPorId(String idMarca) throws Exception;
}
