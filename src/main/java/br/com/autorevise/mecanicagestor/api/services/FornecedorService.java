package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Fornecedor;
import br.com.msoficinas.api.web.request.FornecedorRequest;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface FornecedorService {
    Fornecedor salvarFornecedorDoEstabelecimento(FornecedorRequest request) throws Exception;

    List<Fornecedor> carregarFornecedoresPeloEstabelecimento(String idEstabelecimento);

    void editarFornecedor(FornecedorRequest request) throws Exception;

    void excluirFornecedorDoEstabelecimento(String idEstabelecimento, String idFornecedor) throws Exception;

    Fornecedor buscarFornecedor(String idFornecedor, String idEstabelecimento) throws Exception;
}
