package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Produto;
import br.com.autorevise.mecanicagestor.api.enuns.CategoriaProduto;
import br.com.autorevise.mecanicagestor.api.repositories.ProdutoRepository;
import br.com.autorevise.mecanicagestor.api.services.EstabelecimentoService;
import br.com.autorevise.mecanicagestor.api.services.FornecedorService;
import br.com.autorevise.mecanicagestor.api.services.ProdutoService;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.autorevise.mecanicagestor.api.web.mappers.ProdutoMapper;
import br.com.autorevise.mecanicagestor.api.web.request.ProdutoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ProdutoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Override
    public Produto addProdutoParaEstabelecimento(ProdutoRequest request) throws Exception {
        var produto = produtoMapper.converterRequestParaModelo(request);
        var estabelecimento = estabelecimentoService.buscarEstabelecimentoPeloId(request.idEstabelecimento());
        produto.setEstabelecimento(estabelecimento);
        return produtoRepository.save(produto);
    }

    @Override
    public void atualizarProduto(Produto produto) {
        produtoRepository.saveAndFlush(produto);
    }

    @Override
    public void atualizarProdutoByRequest(ProdutoRequest request) throws Exception {
        var produtoExistente = this.carregarProdutoPeloId(request.idProduto(), request.idEstabelecimento());

        produtoExistente.setCodigoProduto(request.codigo());
        produtoExistente.setCategoriaProduto(CategoriaProduto.valueOf(request.categoria().trim()));
        produtoExistente.setDescricaoProduto(request.descricao());;
        produtoExistente.setReferenciaProduto(request.referencia());
        produtoExistente.setPercentualLucro(request.percentualLucro());
        produtoRepository.saveAndFlush(produtoExistente);
    }

    @Override
    public Produto carregarProdutoPeloId(String idProduto, String idEstabelecimento) throws Exception {
        return produtoRepository.findByIdAndEstabelecimentoId(idProduto, idEstabelecimento)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum produto encontrado com este id "+idProduto));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProdutoListResponse> carregarProdutosDoEstabelecimento(String idEstabelecimento) {
        return produtoRepository.findProdutosByEstabelecimento(idEstabelecimento).stream()
                .map(produto -> produtoMapper.converterModeloParaResponseList(produto)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public ProdutoListResponse carregarProdutoPeloSeuCodigo(String codigo, String idEstabelecimento) {
        return produtoRepository.findByCodigoProdutoAndEstabelecimentoId(codigo, idEstabelecimento)
                .map(produto -> produtoMapper.converterModeloParaResponseList(produto))
                .orElse(null);
    }

    @Override
    public ProdutoListResponse carregarProdutoPeloIdProdutoMaisIdEstabelecimento(String idProduto, String idEstabelecimento) throws Exception {
        return produtoRepository.findByIdAndEstabelecimentoId(idProduto, idEstabelecimento)
                .map(produto -> produtoMapper.converterModeloParaResponseList(produto))
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum produto encontrado com este id %s para este estabelecimento", idProduto)));
    }
}
