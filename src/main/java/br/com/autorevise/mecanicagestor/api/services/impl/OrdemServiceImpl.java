package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Ordem;
import br.com.msoficinas.api.entidades.*;
import br.com.msoficinas.api.enuns.FormaDePagamento;
import br.com.msoficinas.api.enuns.OrdemStatus;
import br.com.msoficinas.api.enuns.StatusOficina;
import br.com.msoficinas.api.enuns.TipoDeProposta;
import br.com.msoficinas.api.repositories.OrdemRepository;
import br.com.msoficinas.api.repositories.ProdutoRepository;
import br.com.msoficinas.api.repositories.ServicoRepository;
import br.com.msoficinas.api.services.*;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.mappers.OrdemMapper;
import br.com.msoficinas.api.web.request.AtualizarOrdemStatusRequest;
import br.com.msoficinas.api.web.request.AtualizarStatusOficinaRequest;
import br.com.msoficinas.api.web.request.OrdemRequest;
import br.com.msoficinas.api.web.response.OrdemResponseList;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

        if (request.veiculo() != null) {
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
                .collect(Collectors.toList());
    }

    @Override
    public void atualizarStatusOrdemEstabelecimento(AtualizarOrdemStatusRequest request) throws Exception {
        Ordem ordem = ordemRepository.findById(request.idOrdem())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum ordem encontrado com este ID "+request.idOrdem()));

        if (OrdemStatus.valueOf(request.novoStatus()).equals(OrdemStatus.EE)) {
            ordem.setStatusOficina(StatusOficina.AG);
        }

        ordem.setOrdemStatus(OrdemStatus.valueOf(request.novoStatus()));
        ordemRepository.save(ordem);
    }

    @Override
    public void atualizarStatusOficinaDoOrdemDoEstabelecimento(AtualizarStatusOficinaRequest request) throws Exception {
        Ordem ordem = ordemRepository.findById(request.idOrdem())
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum ordem encontrado com este ID "+request.idOrdem()));

        if (StatusOficina.CO.equals(StatusOficina.valueOf(request.statusOficina()))) {
            ordem.setStatusOficina(StatusOficina.CO);
            ordem.setOrdemStatus(OrdemStatus.AP);
        } else if (StatusOficina.PE.equals(StatusOficina.valueOf(request.statusOficina()))) {
            ordem.setStatusOficina(StatusOficina.PE);
            ordem.setOrdemStatus(OrdemStatus.PE);
            ordem.getPendencias().add(request.descricaoPendencia());
        } else {
            ordem.setStatusOficina(StatusOficina.valueOf(request.statusOficina()));
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
            System.out.println("*** veiculo novo "+ novoVeiculo.getId());
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
}
