package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.autorevise.mecanicagestor.api.entities.Cliente;
import br.com.autorevise.mecanicagestor.api.entities.Estabelecimento;
import br.com.autorevise.mecanicagestor.api.entities.Veiculo;
import br.com.autorevise.mecanicagestor.api.repositories.ClienteRepository;
import br.com.autorevise.mecanicagestor.api.repositories.VeiculoRepository;
import br.com.autorevise.mecanicagestor.api.services.ClienteService;
import br.com.autorevise.mecanicagestor.api.services.EstabelecimentoService;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoJaRegistradoException;
import br.com.autorevise.mecanicagestor.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.autorevise.mecanicagestor.api.web.mappers.ClienteMapper;
import br.com.autorevise.mecanicagestor.api.web.request.AddVeiculoClienteRequest;
import br.com.autorevise.mecanicagestor.api.web.request.ClienteAddRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ClienteResponse;
import br.com.autorevise.mecanicagestor.api.web.response.FormClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public Cliente cadastrarCliente(ClienteAddRequest request) throws Exception {
        // Busca o estabelecimento e modelo
        Estabelecimento estabelecimento = estabelecimentoService.buscarEstabelecimentoPeloId(request.idEstabelecimento());

        // Busca ou cria o cliente
        Cliente cliente = clienteRepository.findByCpfCnpj(request.cpfOuCnpj())
                .orElseGet(() -> clienteMapper.converterRequestParaModel(request));
        boolean clienteExistente = clienteRepository.findByCpfCnpj(request.cpfOuCnpj()).isPresent();

        // Associa veículo ao cliente se necessário
        if (cliente.getVeiculos() == null) {
            cliente.setVeiculos(new HashSet<>());
        }

        // Associa estabelecimento ao cliente
        if (cliente.getEstabelecimentos() == null) {
            cliente.setEstabelecimentos(new HashSet<>());
        }
        if (!cliente.getEstabelecimentos().contains(estabelecimento)) {
            cliente.getEstabelecimentos().add(estabelecimento);
            // Se o relacionamento for bidirecional, adicione:
             if (estabelecimento.getClientes() == null) {
                 estabelecimento.setClientes(new HashSet<>());
             }
             estabelecimento.getClientes().add(cliente);
        }

        return clienteRepository.save(cliente);
    }

    @Override
    public FormClienteResponse buscaClienteParaEstabelecimentoPeloCpfOuCnpj(String cpfOuCnpj) throws Exception {
        return clienteRepository.findByCpfCnpj(cpfOuCnpj)
                .map(cliente -> clienteMapper.converterModelParaClienteFormResponse(cliente))
                .orElse(null);
    }

    @Override
    public Cliente adicionarVeiculoParaCliente(AddVeiculoClienteRequest request) throws Exception {

        Optional<Veiculo> optional = veiculoRepository.findByClienteIdAndPlaca(request.idCliente(), request.veiculo().placa());

        if (optional.isPresent())
            throw new ObjetoJaRegistradoException(String.format("Existe associação deste cliente com a veículo com placa", request.veiculo().placa()));

        Optional<Cliente> optionalCliente = clienteRepository.findById(request.idCliente());

        if (optionalCliente.isEmpty())
            throw new ObjetoNaoEncontradoException(String.format("Nenhum cliente encontrado com este id %s informado.", request.idCliente()));

        Veiculo veiculo = Veiculo.builder()
                .ano(request.veiculo().ano())
                .placa(request.veiculo().placa())
                .build();
        veiculo.setClientes(Set.of(optionalCliente.get()));
        optionalCliente.get().setVeiculos(Set.of(veiculo));

        return clienteRepository.save(optionalCliente.get());
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteResponse buscaClienteParaEstabelecimentoPeloId(String idCliente) throws Exception {
        return clienteRepository.findById(idCliente).map(cliente -> clienteMapper.converterModelParaClienteResponse(cliente))
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum cliente encontrado com este ID informado. ID '%s'", idCliente)));
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente buscarClientePeloCpfOuCnpj(String cpfCnpj) {
        var cliente = clienteRepository.findByCpfCnpj(cpfCnpj).orElse(null);
        System.out.println("**** "+ cliente.getNomeCompleto());
        return cliente;
    }

    @Override
    public Cliente buscarClientePeloId(String idCliente) throws Exception {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum cliente encontrado com este id "+idCliente));
    }
}
