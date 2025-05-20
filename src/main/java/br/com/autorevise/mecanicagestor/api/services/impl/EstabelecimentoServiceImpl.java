package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Cliente;
import br.com.autorevise.mecanicagestor.api.entities.Estabelecimento;
import br.com.autorevise.mecanicagestor.api.entities.Usuario;
import br.com.autorevise.mecanicagestor.api.enuns.Perfil;
import br.com.autorevise.mecanicagestor.api.enuns.Plano;
import br.com.autorevise.mecanicagestor.api.repositories.ClienteRepository;
import br.com.autorevise.mecanicagestor.api.repositories.EstabelecimentoRepository;
import br.com.autorevise.mecanicagestor.api.services.ClienteService;
import br.com.autorevise.mecanicagestor.api.services.EstabelecimentoService;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.autorevise.mecanicagestor.api.web.mappers.ClienteMapper;
import br.com.autorevise.mecanicagestor.api.web.mappers.EstabelecimentoMapper;
import br.com.autorevise.mecanicagestor.api.web.request.EstabelecimentoRequest;
import br.com.autorevise.mecanicagestor.api.web.request.UpdatePlanoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ClienteResponse;
import br.com.autorevise.mecanicagestor.api.web.response.EstabelecimentoDadosResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoServiceImpl implements EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteMapper clienteMapper;

    @Transactional
    @Override
    public Estabelecimento cadastrarEstabelecimento(EstabelecimentoRequest request) {

        Optional<Estabelecimento> optional = estabelecimentoRepository.findByCpfOuCnpj(request.documento());
        Cliente cliente = clienteRepository.findByCpfCnpj("00000000000").get();

        if(!optional.isPresent()) {
            var estabelecimento = estabelecimentoMapper.converterRequestParaModel(request);
            var usuario = Usuario.builder()
                    .nomeCompleto(request.proprietario())
                    .cpf(request.cpfProprietario())
                    .ativo(true)
                    .senha(passwordEncoder.encode(request.cpfProprietario().substring(0, 6)))
                    .perfil(Perfil.PROP)
                    .usuarioPrincipal(true)
                    .estabelecimentos(Set.of(estabelecimento))
                    .build();
            estabelecimento.setUsuarios(Set.of(usuario));

            estabelecimento.setClientes(Set.of(cliente));

            // Se necessário, inicializa a coleção do cliente (garantia extra)
            if (cliente.getEstabelecimentos() == null) {
                cliente.setEstabelecimentos(new HashSet<>());
            }

            cliente.getEstabelecimentos().add(estabelecimento);
            return estabelecimentoRepository.save(estabelecimento);
        } else {
            throw new IllegalStateException("Estabelecimento com documento " + request.documento() + " já existe.");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Estabelecimento buscarEstabelecimentoPeloId(String idEstabelecimento) throws Exception {
        return estabelecimentoRepository.findById(idEstabelecimento)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum estabelecimento encontrado com este id '%s'",idEstabelecimento)));
    }

    @Transactional(readOnly = true)
    @Override
    public EstabelecimentoDadosResponse carregarDadosDoEstabelecimento(String idEstabelecimento) throws Exception {
        return estabelecimentoRepository.findById(idEstabelecimento)
                .map(estabelecimento -> estabelecimentoMapper.converterModelEmResponse(estabelecimento))
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum estabelecimento encontrado com este id '%s'",idEstabelecimento)));
    }

    @Transactional(readOnly = true)
    @Override
    public Set<ClienteResponse> carregarClientesDoEstabelecimento(String idEstabelecimento) {
        var clientes = estabelecimentoRepository.findById(idEstabelecimento).get().getClientes();
        return clientes.stream().map(cliente -> clienteMapper.converterModelParaClienteResponse(cliente)).collect(Collectors.toSet());
    }

    @Override
    public ClienteResponse carregarClientePeloEstabelecimento(String idEstabelecimento, String idCliente) throws Exception {
        return estabelecimentoRepository.findByIdAndClienteId(idCliente, idEstabelecimento)
                .map(cliente -> clienteMapper.converterModelParaClienteResponse(cliente))
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Este cliente não esta associado a este estabelecimento"));
    }

    @Override
    public void updatePlanoEstabelecimento(UpdatePlanoRequest request)  throws Exception {
        var estabelecimento = this.buscarEstabelecimentoPeloId(request.idEstabelecimento());
        estabelecimento.setPlano(Plano.valueOf(request.novoPlano()));
        estabelecimentoRepository.save(estabelecimento);
    }
}
