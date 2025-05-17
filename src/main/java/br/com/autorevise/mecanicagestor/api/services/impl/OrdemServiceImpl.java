package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.*;
import br.com.autorevise.mecanicagestor.api.enuns.FormaDePagamento;
import br.com.autorevise.mecanicagestor.api.enuns.OrdemStatus;
import br.com.autorevise.mecanicagestor.api.enuns.StatusOficina;
import br.com.autorevise.mecanicagestor.api.enuns.TipoDeProposta;
import br.com.autorevise.mecanicagestor.api.repositories.OrdemRepository;
import br.com.autorevise.mecanicagestor.api.repositories.ProdutoRepository;
import br.com.autorevise.mecanicagestor.api.repositories.ServicoRepository;
import br.com.autorevise.mecanicagestor.api.services.*;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.autorevise.mecanicagestor.api.web.mappers.OrdemMapper;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarOrdemStatusRequest;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarStatusOficinaRequest;
import br.com.autorevise.mecanicagestor.api.web.request.OrdemRequest;
import br.com.autorevise.mecanicagestor.api.web.response.OrdemResponseList;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrdemServiceImpl implements OrdemService {

    @Autowired
    private OrdemRepository ordemRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private OrdemMapper ordemMapper;

    @Override
    public Ordem registrarOrdemEstabelecimento(OrdemRequest request) throws Exception {

        Estabelecimento estabelecimento = estabelecimentoService.buscarEstabelecimentoPeloId(request.idEstabelecimento());
        Usuario usuario = usuarioService.buscarUsuarioPeloCpf(request.cpfResponsavel());
        Cliente cliente = clienteService.buscarClientePeloId(request.idCliente());
        Veiculo veiculo = null;

        if (!request.veiculo().placa().isBlank()) {
            veiculo = getOrRegisterVeiculo(request, cliente);
        }

        Ordem ordem = Ordem.builder()
            .estabelecimento(estabelecimento)
            .cliente(cliente)
            .veiculo(veiculo)
            .desconto(Optional.ofNullable(request.desconto()).orElse(0.0))
            .valorEntrada(Optional.ofNullable(request.valorEntrada()).orElse(0.0))
            .formaDePagamento(FormaDePagamento.valueOf(request.formaPagamento()))
            .observacoes(request.observacoes())
            .tipoProposta(TipoDeProposta.valueOf(request.tipoProposta()))
            .totalDiasProposta(request.diasValidade())
            .ordemStatus(OrdemStatus.valueOf(request.statusPedido()))
            .responsavel(usuario)
            .build();

        ordem.setProdutos(getProdutosSelecionados(request, ordem));
        ordem.setServicos(getServicosSelecionados(request));

        return ordemRepository.save(ordem);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrdemResponseList> carregarOrdemEstabelecimento(String idEstabelecimento) {
        return ordemRepository.findOrdemByEstabelecimento(idEstabelecimento)
                .stream().map(ordem -> ordemMapper.converterModelParaResponseList(ordem))
                .toList();
    }

    @Transactional
    @Override
    public void atualizarStatusOrdemEstabelecimento(AtualizarOrdemStatusRequest request) throws Exception {

        Ordem ordem = ordemRepository.findById(request.idOrdem())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum ordem encontrado com este ID "+request.idOrdem()));

        if (OrdemStatus.valueOf(request.novoStatus()).equals(OrdemStatus.EE)) {
            ordem.setStatusOficina(StatusOficina.AG);
        }

        if (OrdemStatus.valueOf(request.novoStatus()).equals(OrdemStatus.FI) && ordem.getStatusOficina() == StatusOficina.NA) {
            baixaNoEstoqueDoEstabelecimento(ordem);
        }
        ordem.setOrdemStatus(OrdemStatus.valueOf(request.novoStatus()));
        ordemRepository.save(ordem);
    }

    @Transactional
    @Override
    public void atualizarStatusOficinaDoOrdemDoEstabelecimento(AtualizarStatusOficinaRequest request) throws Exception {
        Ordem ordem = ordemRepository.findById(request.idOrdem())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum ordem encontrado com este ID "+request.idOrdem()));

        System.out.println("*** **** "+ request.toString());

        if (StatusOficina.valueOf(request.statusOficina()).equals(StatusOficina.EA)) {
            baixaNoEstoqueDoEstabelecimento(ordem);
            ordem.setStatusOficina(StatusOficina.EA);
        }

        if (StatusOficina.valueOf(request.statusOficina()).equals(StatusOficina.CO)) {
            ordem.setStatusOficina(StatusOficina.CO);
            ordem.setOrdemStatus(OrdemStatus.AP);
        }

        if (StatusOficina.valueOf(request.statusOficina()).equals(StatusOficina.PE)) {
            ordem.setStatusOficina(StatusOficina.PE);
            ordem.setOrdemStatus(OrdemStatus.PE);
        }

         ordemRepository.save(ordem);
    }

    @Override
    public Ordem carregarOrdemDoEstabelecimentoPeloSeuIdOrdem(String idOrdem, String idEstabelecimento) throws Exception {
        return ordemRepository.findByIdAndEstabelecimentoId(idOrdem, idEstabelecimento)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(""));
    }

    @Override
    public void atualizarOrdemTipoProposta(String idOrdem) throws Exception {
        Ordem ordem = ordemRepository.findById(idOrdem)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum ordem encontrado com este ID "+idOrdem));
        ordem.setTipoProposta(TipoDeProposta.P);
        ordem.setOrdemStatus(OrdemStatus.AU);
        ordemRepository.save(ordem);
    }

    private Veiculo getOrRegisterVeiculo(OrdemRequest request, Cliente cliente) throws Exception {
        if (request.veiculo().idVeiculo() == null || request.veiculo().idVeiculo().isEmpty()) {
            Veiculo novoVeiculo = veiculoService.registrarVeiculo(request.veiculo());
            veiculoService.vincularVeiculoComCliente(cliente, novoVeiculo);
            return novoVeiculo;
        } else {
            Veiculo veiculoExistente = veiculoService.buscarVeiculoPelaPlaca(request.veiculo().placa());
            if (!veiculoService.veiculoEstaViculadoComEsteCliente(cliente, request.veiculo().placa())) {
                veiculoService.vincularVeiculoComCliente(cliente, veiculoExistente);
            }
            return veiculoExistente;
        }
    }

    private Set<OrdemProdutos> getProdutosSelecionados(OrdemRequest request, Ordem ordem) {
        Set<OrdemProdutos> produtos = new HashSet<>();
        request.produtos().forEach(p -> {
            Produto produto = produtoRepository.findById(p.idProduto())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + p.idProduto()));
            produtos.add(OrdemProdutos.builder()
                    .ordem(ordem)
                    .produto(produto)
                    .quantidade(p.quantidade())
                    .build());
        });
        return produtos;
    }

    private Set<ServicoEstabelecimento> getServicosSelecionados(OrdemRequest request) {
        Set<ServicoEstabelecimento> servicos = new HashSet<>();
        request.servicos().forEach(s -> {
            ServicoEstabelecimento servico = servicoRepository.findById(s.idServico())
                    .orElseThrow(() -> new EntityNotFoundException("Serviço não encontrado: " + s.idServico()));
            servicos.add(servico);
        });
        return servicos;
    }

    private void baixaNoEstoqueDoEstabelecimento(Ordem ordem) {
        ordem.getProdutos().forEach(item -> {
            Optional<Produto> produtoOptional = produtoRepository.findById(item.getProduto().getId());

            if(produtoOptional.isEmpty()) throw new RuntimeException("Produto com ID " + produtoOptional.get().getId() + " não encontrado.");

            Produto produto = produtoOptional.get();

            if(produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getDescricaoProduto());
            }
            System.out.println("Produto: "+item.getProduto().getDescricaoProduto()+" quantidade: "+item.getQuantidade());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        });
    }
}
