package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.EstoqueProduto;
import br.com.autorevise.mecanicagestor.api.entities.Fornecedor;
import br.com.autorevise.mecanicagestor.api.entities.Produto;
import br.com.autorevise.mecanicagestor.api.repositories.EstoqueRepository;
import br.com.autorevise.mecanicagestor.api.services.EstoqueService;
import br.com.autorevise.mecanicagestor.api.services.FornecedorService;
import br.com.autorevise.mecanicagestor.api.services.ProdutoService;
import br.com.autorevise.mecanicagestor.api.web.mappers.EstoqueMapper;
import br.com.autorevise.mecanicagestor.api.web.request.EstoqueRequest;
import br.com.autorevise.mecanicagestor.api.web.response.EstoqueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueServicoImpl implements EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FornecedorService fornecedorService;

    @Autowired
    private EstoqueMapper estoqueMapper;

    @Override
    public EstoqueProduto addEstoqueEmProduto(EstoqueRequest request) throws Exception {

        Produto produto = produtoService.carregarProdutoPeloId(request.idProduto(), request.idEstabelecimento());
        Fornecedor fornecedor = fornecedorService.buscarFornecedor(request.idFornecedor(), request.idEstabelecimento());

        var estoque = criarRegistroEstoque(request, produto, fornecedor);

        if (produto.getPrecoCusto() < request.precoCusto()) {
            produto.setPrecoCusto(request.precoCusto());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + request.quantidade());
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + request.quantidade());
        }
        var estoqueDB = estoqueRepository.save(estoque);
        produtoService.atualizarProduto(produto);
        return estoqueDB;
    }

    @Override
    public void atualizarHistoricoEstoque(EstoqueRequest request) {

    }

    @Override
    public List<EstoqueResponse> carregarHistoricoEstoqueProduto(String idProduto) {
        return estoqueRepository.findByProdutoId(idProduto)
                .stream()
                .map(e -> estoqueMapper.converterModeloEmResponse(e)).collect(Collectors.toList());
    }

    private static EstoqueProduto criarRegistroEstoque(EstoqueRequest request, Produto produto, Fornecedor fornecedor) {
        return EstoqueProduto.builder()
                .precoDeCusto(request.precoCusto())
                .quantidade(request.quantidade())
                .quantidadeAnterior(produto.getQuantidadeEstoque())
                .fornecedor(fornecedor)
                .produto(produto)
                .build();
    }
}
