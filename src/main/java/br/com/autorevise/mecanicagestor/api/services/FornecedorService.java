package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Fornecedor;
import br.com.autorevise.mecanicagestor.api.web.request.FornecedorRequest;

import java.util.List;

public interface FornecedorService {
    Fornecedor salvarFornecedorDoEstabelecimento(FornecedorRequest request) throws Exception;

    List<Fornecedor> carregarFornecedoresPeloEstabelecimento(String idEstabelecimento);

    void editarFornecedor(FornecedorRequest request) throws Exception;

    void excluirFornecedorDoEstabelecimento(String idEstabelecimento, String idFornecedor) throws Exception;

    Fornecedor buscarFornecedor(String idFornecedor, String idEstabelecimento) throws Exception;
}
