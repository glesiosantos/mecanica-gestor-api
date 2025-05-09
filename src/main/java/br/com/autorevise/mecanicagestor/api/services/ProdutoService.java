package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Produto;
import br.com.msoficinas.api.web.request.ProdutoRequest;
import br.com.msoficinas.api.web.response.ProdutoListResponse;

import java.util.List;

public interface ProdutoService {

    Produto addProdutoParaEstabelecimento(ProdutoRequest request) throws Exception;

    void atualizarProduto(Produto produto);

    void atualizarProdutoByRequest(ProdutoRequest request) throws Exception;

    Produto carregarProdutoPeloId(String idProduto, String idEstabelecimento) throws Exception;

    List<ProdutoListResponse> carregarProdutosDoEstabelecimento(String idEstabelecimento);

    ProdutoListResponse carregarProdutoPeloSeuCodigo(String codigo, String idEstabelecimento);

    ProdutoListResponse carregarProdutoPeloIdProdutoMaisIdEstabelecimento(String idProduto, String idEstabelecimento) throws Exception;
}
