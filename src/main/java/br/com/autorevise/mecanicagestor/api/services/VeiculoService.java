package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Cliente;
import br.com.autorevise.mecanicagestor.api.entities.Veiculo;
import br.com.autorevise.mecanicagestor.api.web.request.VeiculoRequest;
import br.com.autorevise.mecanicagestor.api.web.response.VeiculoResponse;

import java.util.List;

public interface VeiculoService {

    Veiculo buscarVeiculoPelaPlaca(String placa);

    List<VeiculoResponse> carregarVeiculosRegistrados();

    Veiculo registrarVeiculo(VeiculoRequest request) throws Exception;

    void vincularVeiculoComCliente(Cliente cliente, Veiculo veiculo);

    boolean veiculoEstaViculadoComEsteCliente(Cliente cliente, String placa);
}
