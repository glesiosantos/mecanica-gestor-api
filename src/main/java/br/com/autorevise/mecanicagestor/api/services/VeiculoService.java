package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Cliente;
import br.com.msoficinas.api.entidades.Veiculo;
import br.com.msoficinas.api.web.request.VeiculoRequest;
import br.com.msoficinas.api.web.response.VeiculoResponse;

import java.util.List;

public interface VeiculoService {

    Veiculo buscarVeiculoPelaPlaca(String placa);

    List<VeiculoResponse> carregarVeiculosRegistrados();

    Veiculo registrarVeiculo(VeiculoRequest request) throws Exception;

    void vincularVeiculoComCliente(Cliente cliente, Veiculo veiculo);

    boolean veiculoEstaViculadoComEsteCliente(Cliente cliente, String placa);
}
