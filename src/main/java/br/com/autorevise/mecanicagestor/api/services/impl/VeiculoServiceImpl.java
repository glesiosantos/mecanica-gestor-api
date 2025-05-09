package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.entidades.Cliente;
import br.com.msoficinas.api.entidades.Veiculo;
import br.com.msoficinas.api.repositories.ClienteRepository;
import br.com.msoficinas.api.repositories.VeiculoRepository;
import br.com.msoficinas.api.services.ClienteService;
import br.com.msoficinas.api.services.MarcaService;
import br.com.msoficinas.api.services.VeiculoService;
import br.com.msoficinas.api.web.mappers.VeiculoMapper;
import br.com.msoficinas.api.web.request.VeiculoRequest;
import br.com.msoficinas.api.web.response.VeiculoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private VeiculoMapper veiculoMapper;

    @Transactional(readOnly = true)
    @Override
    public Veiculo buscarVeiculoPelaPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa).orElse(null);
    }

    @Override
    public List<VeiculoResponse> carregarVeiculosRegistrados() {
        return veiculoRepository.findAll()
                .stream().map(veiculo -> veiculoMapper.converterModeloEmResponseList(veiculo)).collect(Collectors.toList());
    }

    @Override
    public Veiculo registrarVeiculo(VeiculoRequest request) throws Exception {
        var marca = marcaService.buscarMarcaPorId(request.marca());
        var veiculo = veiculoMapper.converterRequestEmModel(request);
        veiculo.setMarca(marca);
        return veiculoRepository.save(veiculo);
    }

    @Override
    public void vincularVeiculoComCliente(Cliente cliente, Veiculo veiculo) {

        if (cliente.getVeiculos() == null) cliente.setVeiculos(new HashSet<>());
        if (veiculo.getClientes() == null) veiculo.setClientes(new HashSet<>());

        cliente.getVeiculos().add(veiculo);
        veiculo.getClientes().add(cliente);
        veiculoRepository.save(veiculo);
        clienteRepository.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean veiculoEstaViculadoComEsteCliente(Cliente cliente, String placa) {
        return veiculoRepository.existsByPlacaAndClienteId(placa, cliente.getId());
    }

}
